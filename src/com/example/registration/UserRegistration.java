package com.example.registration;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRegistration {

    private final DatabaseManager db;

    public UserRegistration(DatabaseManager databaseManager) {
        this.db = databaseManager;
    }

    public void registerUserManually() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String fname = sc.next();

        System.out.print("Enter last name: ");
        String lname = sc.next();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter password: ");
        String password = sc.next();


        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO \"user\" (fname, lname, age, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, fname);
                preparedStatement.setString(2, lname);
                preparedStatement.setInt(3, age);
                preparedStatement.setString(4, password);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("User registered successfully.");
                } else {
                    System.out.println("User registration failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
