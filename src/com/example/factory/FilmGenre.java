package com.example.factory;

import java.sql.SQLException;

public class FilmGenre {

    public Genres chooseGenre(String genre) throws SQLException {
        if (genre.equalsIgnoreCase("Action")) {
            return new Action();
        } else if (genre.equalsIgnoreCase("Comedy")) {
            return new Comedy();
        } else if (genre.equalsIgnoreCase("Crime")) {
            return new Crime();
        } else if (genre.equalsIgnoreCase("Drama")) {
            return new Drama();
        } else {
            return null;
        }
    }
}
