package src.backend;

import src.backend.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubManager {

    /**
     * Promotes a Normal User to Club President and assigns them a new club.
     * 
     * @param email      The email of the user to promote.
     * @param clubName   The name of the new club.
     * @param maxMembers The maximum number of members for the club.
     * @param category   The activity category of the club.
     * @return true if successful, false otherwise.
     */
    public static boolean createClub(String email, String clubName, int maxMembers, String category) {
        if (email == null || clubName == null || clubName.trim().isEmpty()) {
            return false;
        }
        email = email.trim().toLowerCase();
        clubName = clubName.trim();

        String checkCountSql = "SELECT clubs_joined FROM members WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
        String checkNameSql = "SELECT 1 FROM clubs WHERE LOWER(TRIM(name)) = LOWER(TRIM(?))";
        String insertClubSql = "INSERT INTO clubs (name, president_email, max_members, activity_category) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 0. Auto-repair for orphan users (exists in users but missing from members)
            String verifyMemberSql = "SELECT 1 FROM members WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
            try (PreparedStatement verifyStmt = conn.prepareStatement(verifyMemberSql)) {
                verifyStmt.setString(1, email);
                try (java.sql.ResultSet rs = verifyStmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Auto-repair in createClub: Creating member record for " + email);
                        String repairSql = "INSERT INTO members (email, first_name, last_name, clubs_joined) VALUES (?, 'Member', 'User', 0)";
                        try (PreparedStatement repairStmt = conn.prepareStatement(repairSql)) {
                            repairStmt.setString(1, email);
                            repairStmt.executeUpdate();
                        }
                    }
                }
            }

            // 0. Check club limit
            try (PreparedStatement checkStmt = conn.prepareStatement(checkCountSql)) {
                checkStmt.setString(1, email);
                try (java.sql.ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("clubs_joined");
                        if (count >= 2) {
                            return false; // Limit reached
                        }
                    }
                }
            }

            // 0b. Check if club name already exists
            try (PreparedStatement checkNameStmt = conn.prepareStatement(checkNameSql)) {
                checkNameStmt.setString(1, clubName);
                try (java.sql.ResultSet rs = checkNameStmt.executeQuery()) {
                    if (rs.next()) {
                        return false; // Name exists
                    }
                }
            }

            // 1. Insert into clubs table
            try (PreparedStatement startClubStmt = conn.prepareStatement(insertClubSql)) {
                startClubStmt.setString(1, clubName);
                startClubStmt.setString(2, email);
                startClubStmt.setInt(3, maxMembers);
                startClubStmt.setString(4, category);
                startClubStmt.executeUpdate();
            }

            // 2. Add to member_clubs (with role 'Club President')
            String insertMemberClubSql = "INSERT INTO member_clubs (email, club_name, role) VALUES (?, ?, 'Club President')";
            try (PreparedStatement insertMemberClubStmt = conn.prepareStatement(insertMemberClubSql)) {
                insertMemberClubStmt.setString(1, email);
                insertMemberClubStmt.setString(2, clubName);
                insertMemberClubStmt.executeUpdate();
            }

            // 3. Increment clubs_joined in members table
            String incrementClubsJoinedSql = "UPDATE members SET clubs_joined = clubs_joined + 1 WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
            try (PreparedStatement incStmt = conn.prepareStatement(incrementClubsJoinedSql)) {
                incStmt.setString(1, email);
                incStmt.executeUpdate();
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

    /**
     * Retrieves a list of all available clubs.
     * 
     * @return List of club names.
     */
    public static java.util.List<String> getAllClubs() {
        java.util.List<String> clubs = new java.util.ArrayList<>();
        String sql = "SELECT name FROM clubs";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                clubs.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    /**
     * Assigns a user to a club.
     * 
     * @param email    The email of the user.
     * @param clubName The name of the club to join.
     * @return true if successful, false otherwise.
     */
    public static boolean joinClub(String email, String clubName) {
        if (email == null || clubName == null) {
            return false;
        }
        email = email.trim().toLowerCase();
        clubName = clubName.trim();

        String checkExistingSql = "SELECT 1 FROM member_clubs WHERE LOWER(TRIM(email)) = LOWER(TRIM(?)) AND LOWER(TRIM(club_name)) = LOWER(TRIM(?))";
        String sql = "INSERT INTO member_clubs (email, club_name, role) VALUES (?, ?, 'Normal User')";
        String updateCountSql = "UPDATE members SET clubs_joined = clubs_joined + 1 WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 0. Check if already in this club
            try (PreparedStatement checkExistingStmt = conn.prepareStatement(checkExistingSql)) {
                checkExistingStmt.setString(1, email);
                checkExistingStmt.setString(2, clubName);
                try (java.sql.ResultSet rs = checkExistingStmt.executeQuery()) {
                    if (rs.next()) {
                        return false; // Already a member!
                    }
                }
            }

            // 0b. President Membership Limit: Cap at 2 total clubs if they are a lead
            if (!getOwnedClubs(email).isEmpty()) {
                String checkCountSql = "SELECT clubs_joined FROM members WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkCountSql)) {
                    checkStmt.setString(1, email);
                    try (java.sql.ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt("clubs_joined");
                            if (count >= 2) {
                                return false; // Limit reached for presidents
                            }
                        }
                    }
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, clubName);
                pstmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateCountSql)) {
                updateStmt.setString(1, email);
                updateStmt.executeUpdate();
            }

            conn.commit();
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

    /**
     * Retrieves a list of members in the club managed by the given president email.
     * 
     * @param presidentEmail The email of the club president.
     * @return List of strings containing member names and emails.
     */
    public static java.util.List<String> getMembersByPresident(String presidentEmail) {
        java.util.List<String> members = new java.util.ArrayList<>();
        java.util.List<String> ownedClubs = getOwnedClubs(presidentEmail);
        String membersSql = "SELECT m.first_name, m.last_name, m.email FROM members m " +
                "JOIN member_clubs mc ON LOWER(TRIM(m.email)) = LOWER(TRIM(mc.email)) WHERE LOWER(TRIM(mc.club_name)) = LOWER(TRIM(?))";

        try (Connection conn = DBConnection.getConnection()) {
            for (String clubName : ownedClubs) {
                try (PreparedStatement pstmt = conn.prepareStatement(membersSql)) {
                    pstmt.setString(1, clubName);
                    try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            String name = rs.getString("first_name") + " " + rs.getString("last_name");
                            String email = rs.getString("email");
                            members.add("[" + clubName + "] " + name + " (" + email + ")");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    /**
     * Removes a user from a club.
     * 
     * @param email    The email of the user.
     * @param clubName The name of the club to quit.
     * @return true if successful, false otherwise.
     */
    public static boolean quitClub(String email, String clubName) {
        if (email == null || clubName == null) {
            return false;
        }
        email = email.trim().toLowerCase();
        clubName = clubName.trim();

        // Potential President Check: Users cannot quit the club they lead
        java.util.List<String> ownedClubs = getOwnedClubs(email);
        for (String owned : ownedClubs) {
            if (clubName.trim().equalsIgnoreCase(owned.trim())) {
                return false;
            }
        }

        String sql = "DELETE FROM member_clubs WHERE LOWER(TRIM(email)) = LOWER(TRIM(?)) AND LOWER(TRIM(club_name)) = LOWER(TRIM(?))";
        String updateCountSql = "UPDATE members SET clubs_joined = clubs_joined - 1 WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            int affectedRows = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, clubName);
                affectedRows = pstmt.executeUpdate();
            }

            if (affectedRows > 0) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateCountSql)) {
                    updateStmt.setString(1, email);
                    updateStmt.executeUpdate();
                }
            }

            conn.commit();
            return affectedRows > 0;

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

    /**
     * Retrieves all clubs a user has joined.
     * 
     * @param email The email of the user.
     * @return List of club names.
     */
    public static java.util.List<String> getJoinedClubs(String email) {
        if (email == null)
            return new java.util.ArrayList<>();
        email = email.trim().toLowerCase();
        java.util.List<String> clubs = new java.util.ArrayList<>();
        String sql = "SELECT club_name FROM member_clubs WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    clubs.add(rs.getString("club_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    /**
     * Finds the names of the clubs where the given user is the president.
     * 
     * @param email The email of the president.
     * @return List of club names.
     */
    public static java.util.List<String> getOwnedClubs(String email) {
        if (email == null)
            return new java.util.ArrayList<>();
        email = email.trim().toLowerCase();
        java.util.List<String> clubs = new java.util.ArrayList<>();
        String sql = "SELECT name FROM clubs WHERE LOWER(TRIM(president_email)) = LOWER(TRIM(?))";
        try (Connection conn = DBConnection.getConnection()) {
            // New: Auto-repair for orphan users (exists in users but missing from members)
            String verifyMemberSql = "SELECT 1 FROM members WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
            try (PreparedStatement verifyStmt = conn.prepareStatement(verifyMemberSql)) {
                verifyStmt.setString(1, email);
                try (java.sql.ResultSet rs = verifyStmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Auto-repair: Creating member record for orphan user " + email);
                        String repairSql = "INSERT INTO members (email, first_name, last_name, clubs_joined) VALUES (?, 'Member', 'User', 0)";
                        try (PreparedStatement repairStmt = conn.prepareStatement(repairSql)) {
                            repairStmt.setString(1, email);
                            repairStmt.executeUpdate();
                        }
                    }
                }
            }
            // End of auto-repair block

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        clubs.add(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    /**
     * Legacy helper for backward compatibility or single-club assumptions.
     */
    public static String getPresidentClub(String email) {
        java.util.List<String> owned = getOwnedClubs(email);
        return owned.isEmpty() ? null : owned.get(0);
    }
}
