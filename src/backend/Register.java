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

        String insertMemberSql = "INSERT INTO members (first_name, last_name, email, club, role) VALUES (?, ?, ?, ?, ?)";
        String insertUserSql = "INSERT INTO users (username, password) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Insert Member
            try (PreparedStatement MemberPstmt = conn.prepareStatement(insertMemberSql)) {
                MemberPstmt.setString(1, firstName);
                MemberPstmt.setString(2, lastName);
                MemberPstmt.setString(3, email);
                MemberPstmt.setString(4, club);
                MemberPstmt.setString(5, role);
                MemberPstmt.executeUpdate();
            }

            // 2. Generate Password and Create User
            generatedPassword = generateRandomPassword();
            try (PreparedStatement userPstmt = conn.prepareStatement(insertUserSql)) {
                userPstmt.setString(1, email); // Username is email
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