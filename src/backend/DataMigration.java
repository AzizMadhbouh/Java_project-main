package src.backend;

import src.backend.dao.DBConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DataMigration {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                // Update all emails to lowercase in all tables
                // We do this in an order that respects FKs if possible, or just all at once
                // Note: Oracle doesn't have ON UPDATE CASCADE, so this might be tricky if FKs
                // are strict.
                // If FKs are on email, we might need to disable them, update, then enable.

                System.out.println("Starting email standardization...");

                // Try updating members first. If it fails due to FK, we'll try something else.
                try {
                    stmt.executeUpdate("UPDATE members SET email = LOWER(TRIM(email))");
                    stmt.executeUpdate("UPDATE users SET username = LOWER(TRIM(username))");
                    stmt.executeUpdate("UPDATE member_clubs SET email = LOWER(TRIM(email))");
                    stmt.executeUpdate("UPDATE clubs SET president_email = LOWER(TRIM(president_email))");
                    conn.commit();
                    System.out.println("Successfully standardized all emails to lowercase.");
                } catch (SQLException e) {
                    System.err.println("Migration failed: " + e.getMessage());
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
