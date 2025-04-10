package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:passwords.db";
    private static String sql;
    private static String description;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS passwords (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "password TEXT NOT NULL," +
                    "description TEXT," +
                    "created_at TEXT NOT NULL," +
                    "length INTEGER NOT NULL," +
                    "include_numbers BOOLEAN NOT NULL," +
                    "include_letters BOOLEAN NOT NULL," +
                    "include_special_chars BOOLEAN NOT NULL)";
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePassword(String password, String description, int length, boolean includeNumbers, 
                                  boolean includeLetters, boolean includeSpecialChars) {
        sql = "INSERT INTO passwords (password, description, created_at, length, include_numbers, " +
                    "include_letters, include_special_chars) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, password);
            pstmt.setString(2, description);
            pstmt.setString(3, LocalDateTime.now().format(formatter));
            pstmt.setInt(4, length);
            pstmt.setBoolean(5, includeNumbers);
            pstmt.setBoolean(6, includeLetters);
            pstmt.setBoolean(7, includeSpecialChars);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePassword(String password) {
        sql = "DELETE FROM passwords WHERE password = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLastPasswords(int limit) {
        StringBuilder result = new StringBuilder();
        sql = "SELECT password, description, created_at FROM passwords ORDER BY created_at DESC LIMIT ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, limit);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.append("Password: ").append(rs.getString("password"));
                    description = rs.getString("description");
                    if (description != null && !description.trim().isEmpty()) {
                        result.append(" | Description: ").append(description);
                    }
                    result.append(" | Created: ").append(rs.getString("created_at"))
                          .append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result.toString();
    }
} 