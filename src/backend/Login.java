package src.backend;

public class Login {
    String User;
    String Pass;

    public Login() {
    }

    public static String authenticate(String user, String pass) {
        if (user == null || pass == null)
            return null;

        // Query roles from member_clubs, prioritizing 'Club President'
        // Use LEFT JOIN to allow login even if user has joined 0 clubs
        String sql = "SELECT mc.role FROM users u " +
                "LEFT JOIN member_clubs mc ON LOWER(TRIM(u.username)) = LOWER(TRIM(mc.email)) " +
                "WHERE LOWER(TRIM(u.username)) = LOWER(TRIM(?)) AND u.password = ? " +
                "ORDER BY CASE WHEN mc.role = 'Club President' THEN 1 ELSE 2 END ASC";
        try (java.sql.Connection conn = src.backend.dao.DBConnection.getConnection();
                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            java.sql.ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return (role != null) ? role : "Normal User";
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
