package com.example.factory;

import com.example.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comedy implements Genres{
    private DatabaseManager db;

    public Comedy() {
        this.db = new DatabaseManager();
    }

    @Override
    public void getFilmByGenre() throws SQLException {
        Connection connection = db.getConnection();
        String sql = "SELECT * FROM films WHERE genre = 'Comedy'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\nname: " + rs.getString(1) + "\ngenre: " + rs.getString(2) + "\nrating " + rs.getDouble(3) +
                        "\nyear: "+ rs.getInt(4) + "\nurl: " + rs.getString(5) + "\ndescription: " + rs.getString(6) + "\nreaction: " + rs.getString(7));
            }
        }
    }
}
