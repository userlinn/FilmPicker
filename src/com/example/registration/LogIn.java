package com.example.registration;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn{

    private final DatabaseManager db;

    public LogIn() {
        this.db = new DatabaseManager();
    }

    public boolean userExists(String fname, String lname, int age, String password) {
        String sqlUser = "SELECT * FROM \"user\" WHERE fname = ? AND lname = ? AND age = ? AND password = ?";

        try (Connection connection = db.getConnection()) {
            if (userExistsInTable(connection, sqlUser, fname, lname, age, password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean userExistsInTable(Connection connection, String sql, String fname, String lname, int age, String password) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, password);

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
    }
}
