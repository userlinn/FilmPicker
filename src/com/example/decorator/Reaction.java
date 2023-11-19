package com.example.decorator;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reaction implements Films {

    private DatabaseManager db;

    public Reaction() {
        this.db = new DatabaseManager();
    }

    public void addReaction() {
        try (Connection connection = db.getConnection()) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter film name: ");
            String filmName = sc.nextLine();

            System.out.print("Enter film reaction: ");
            String reaction = sc.nextLine();

            String updateSql = "UPDATE films SET reaction = ? WHERE name = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(updateSql)) {
                pstmt.setString(1, reaction);
                pstmt.setString(2, filmName);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Reaction added successfully!");
                } else {
                    System.out.println("Failed to add reaction. Film not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
