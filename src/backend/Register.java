package src.backend;

public class Register {
    public String firstName;
    public String lastName;
    public String email;
    public String club;

    public Register() {}

    public boolean save() {
        if (firstName == null || lastName == null || email == null) return false;
        return email.contains("@");
    }

    public void cancel() {
        // placeholder for cancel action
    }
}