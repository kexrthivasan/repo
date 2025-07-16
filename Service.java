package com.example.moviedb.service;

import com.example.moviedb.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamService {

    private List<Movie> movies = new ArrayList<>();

    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    public boolean removeMovie(String title) {
        return movies.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

    public Movie getMovie(String title) {
        return movies.stream()
                     .filter(m -> m.getTitle().equalsIgnoreCase(title))
                     .findFirst()
                     .orElse(null);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movies.stream()
                     .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                     .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByDirector(String director) {
        return movies.stream()
                     .filter(m -> m.getDirector().equalsIgnoreCase(director))
                     .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByReleaseYear(int year) {
        return movies.stream()
                     .filter(m -> m.getReleaseYear() == year)
                     .collect(Collectors.toList());
    }

    public List<Movie> getTopRatedMovies(int n) {
        return movies.stream()
                     .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                     .limit(n)
                     .collect(Collectors.toList());
    }

    // Sample data
     @PostConstruct
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

    @SpringBootApplication
public class MovieDbApplication implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(MovieDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        movieService.populateSampleMovies();
    }
}

}
