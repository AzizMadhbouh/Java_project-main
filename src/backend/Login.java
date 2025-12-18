package src.backend;

public class Login {
    String User;
    String Pass;

    public Login() {
    }

    public static String authenticate(String user, String pass) {
        if (user == null || pass == null)
            return null;

        String sql = "SELECT m.role FROM users u JOIN members m ON u.username = m.email WHERE u.username = ? AND u.password = ?";
        try (java.sql.Connection conn = src.backend.dao.DBConnection.getConnection();
                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            java.sql.ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
