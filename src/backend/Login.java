package src.backend;

public class Login {
    String User;
    String Pass;

    public Login() {}

    public static boolean authenticate(String user, String pass) {
        // placeholder authentication logic
        if (user == null || pass == null) return false;
        // simple hardcoded check for demo purposes
        return user.equals("admin") && pass.equals("pass");
    }
}
