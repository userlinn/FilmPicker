package com.example.observer;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayObserver implements Observer{
    private DatabaseManager db;

    public PayObserver() {
        this.db = new DatabaseManager();
    }
    @Override
    public void notifyObserver() {

        String sql = "SELECT * FROM \"user\" ORDER BY fname ASC LIMIT 1";

        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("\nfname: " + rs.getString(1) + "\nlname: " + rs.getString(2) +
                        "\nage: " + rs.getInt(3) + " \npassword: "+ rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
