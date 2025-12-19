package src.backend;

import src.backend.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    public String firstName;
    public String lastName;
    public String email;
    public String club;
    public String role;

    // Store generated password for display
    public String generatedPassword;

    public Register() {
    }

    public boolean save() {
        if (firstName == null || lastName == null || email == null)
            return false;

        String standardizedEmail = email.trim().toLowerCase();

        // Check if user already exists to avoid ORA-00001
        String checkSql = "SELECT 1 FROM users WHERE LOWER(TRIM(username)) = LOWER(TRIM(?)) " +
                "UNION " +
                "SELECT 1 FROM members WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            try (PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
                checkPstmt.setString(1, standardizedEmail);
                checkPstmt.setString(2, standardizedEmail);
                try (java.sql.ResultSet rs = checkPstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Registration failed: User already exists for email " + standardizedEmail);
                        return false;
                    }
                }
            }

            conn.setAutoCommit(false); // Start transaction

            String insertMemberSql = "INSERT INTO members (first_name, last_name, email) VALUES (?, ?, ?)";
            String insertUserSql = "INSERT INTO users (username, password) VALUES (?, ?)";

            // 1. Insert Member (with clubs_joined = 0 initially, will be incremented)
            try (PreparedStatement MemberPstmt = conn.prepareStatement(insertMemberSql)) {
                MemberPstmt.setString(1, firstName);
                MemberPstmt.setString(2, lastName);
                MemberPstmt.setString(3, standardizedEmail);
                MemberPstmt.executeUpdate(); // role is gone, clubs_joined defaults to 0
            }

            // 1b. Insert into member_clubs (with role 'Normal User') ONLY if a club is
            // specified
            if (club != null && !club.trim().isEmpty()) {
                String insertMemberClubSql = "INSERT INTO member_clubs (email, club_name, role) VALUES (?, ?, 'Normal User')";
                try (PreparedStatement mcPstmt = conn.prepareStatement(insertMemberClubSql)) {
                    mcPstmt.setString(1, standardizedEmail);
                    mcPstmt.setString(2, club.trim());
                    mcPstmt.executeUpdate();
                }

                // 1c. Increment clubs_joined to 1
                String updateCountSql = "UPDATE members SET clubs_joined = 1 + clubs_joined WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
                try (PreparedStatement countPstmt = conn.prepareStatement(updateCountSql)) {
                    countPstmt.setString(1, email);
                    countPstmt.executeUpdate();
                }
            }

            // 2. Generate Password and Create User
            generatedPassword = generateRandomPassword();
            try (PreparedStatement userPstmt = conn.prepareStatement(insertUserSql)) {
                userPstmt.setString(1, standardizedEmail); // Username is lowercase email
                userPstmt.setString(2, generatedPassword);
                userPstmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public void cancel() {
        // placeholder for cancel action
    }
}