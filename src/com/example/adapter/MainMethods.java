package com.example.adapter;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMethods implements Methods{

    private DatabaseManager db;

    public MainMethods() {
        this.db = new DatabaseManager();
    }

    @Override
    public void getAllFilms() {
        String sql = "SELECT * FROM films";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllFilmsFull() {
        String sql = "SELECT * FROM films";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFilmByName(String nameChoice) {
        String sql = "SELECT * FROM films WHERE name = '"+nameChoice+"'";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFilmByRating(String ratingChoice) {
        String sql = "SELECT * FROM films WHERE rating = '"+ratingChoice+"'";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFilmByYear(int yearChoice) {
        String sql = "SELECT * FROM films WHERE year = '"+yearChoice+"'";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getRandomFilm() {
        String sql = "SELECT * FROM films ORDER BY RANDOM() LIMIT 1";

        try (Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFilm() {
        try(Connection connection=db.getConnection()){
            Scanner scanner=new Scanner(System.in);

            System.out.print("Enter film name: ");
            String name = scanner.next();

            System.out.print("Enter film genre: ");
            String genre = scanner.next();

            System.out.print("Enter film rating: ");
            double rating = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter film year: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter film url: ");
            String url = scanner.next();

            System.out.print("Enter film description: ");
            String description = scanner.next();

            String insertSql = "INSERT INTO films (name, genre, rating, year, url, description) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, genre);
                pstmt.setDouble(3, rating);
                pstmt.setInt(4, year);
                pstmt.setString(5, url);
                pstmt.setString(6, description);

                pstmt.executeUpdate();

                System.out.println("Film added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm() {
        try (Connection connection = db.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter film name to delete: ");
            String nameToDelete = scanner.nextLine();

            String deleteSql = "DELETE FROM films WHERE name = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(deleteSql)) {
                pstmt.setString(1, nameToDelete);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Film deleted successfully!");
                } else {
                    System.out.println("No film found with the specified name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
