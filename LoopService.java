package com.example.moviedb.service;

import com.example.moviedb.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LoopService {

    private List<Movie> movies = new ArrayList<>();

    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    public boolean removeMovie(String title) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
                movies.remove(i);
                return true;
            }
        }
        return false;
    }

    public Movie getMovie(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> getMoviesByDirector(String director) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getDirector().equalsIgnoreCase(director)) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> getMoviesByReleaseYear(int year) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getReleaseYear() == year) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> getTopRatedMovies(int n) {
        List<Movie> sorted = new ArrayList<>(movies);
        sorted.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getRating(), m1.getRating());
            }
        });

        List<Movie> top = new ArrayList<>();
        for (int i = 0; i < n && i < sorted.size(); i++) {
            top.add(sorted.get(i));
        }
        return top;
    }

    // Sample data
    public void populateSampleMovies() {
        addMovie(new Movie("Inception", "Sci-Fi", "Christopher Nolan", 2010, 8.8));
        addMovie(new Movie("Interstellar", "Sci-Fi", "Christopher Nolan", 2014, 8.6));
        addMovie(new Movie("The Dark Knight", "Action", "Christopher Nolan", 2008, 9.0));
        addMovie(new Movie("The Matrix", "Sci-Fi", "Lana Wachowski", 1999, 8.7));
        addMovie(new Movie("Titanic", "Romance", "James Cameron", 1997, 7.8));
        addMovie(new Movie("Avatar", "Sci-Fi", "James Cameron", 2009, 7.9));
        addMovie(new Movie("Joker", "Drama", "Todd Phillips", 2019, 8.4));
        addMovie(new Movie("Parasite", "Thriller", "Bong Joon-ho", 2019, 8.6));
        addMovie(new Movie("Gladiator", "Action", "Ridley Scott", 2000, 8.5));
        addMovie(new Movie("Fight Club", "Drama", "David Fincher", 1999, 8.8));
    }
}
