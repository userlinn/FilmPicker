package com.example.adapter;

public interface Methods {
    void getAllFilms();
    void getAllFilmsFull();
    void getFilmByName(String nameChoice);
    void getFilmByRating(String ratingChoice);
    void getFilmByYear(int yearChoice);
    void getRandomFilm();
    void addFilm();
    void deleteFilm();
}
