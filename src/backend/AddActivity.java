package src.backend;

public class AddActivity {
    public String activity;
    public String type;
    public String date;

    public AddActivity() {}

    public boolean save() {
        if (activity == null || activity.isBlank()) return false;
        return true;
    }
}
