package com.example.moviedb.controller;

import com.example.moviedb.model.Movie;
import com.example.moviedb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class Controller {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Movie added!";
    }

    @DeleteMapping("/{title}")
    public String deleteMovie(@PathVariable String title) {
        return movieService.removeMovie(title) ? "Deleted" : "Movie not found";
    }

    @GetMapping("/{title}")
    public Movie getMovie(@PathVariable String title) {
        return movieService.getMovie(title);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/director/{director}")
    public List<Movie> getByDirector(@PathVariable String director) {
        return movieService.getMoviesByDirector(director);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getByYear(@PathVariable int year) {
        return movieService.getMoviesByReleaseYear(year);
    }

    @GetMapping("/top/{n}")
    public List<Movie> getTopRated(@PathVariable int n) {
        return movieService.getTopRatedMovies(n);
    }
}
