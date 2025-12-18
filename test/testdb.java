package test;
import src.backend.dao.DBConnection;

public class testdb {
      public static void main(String[] args) {
        try (var conn = DBConnection.getConnection()) {
            System.out.println("Connected to Oracle database successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
