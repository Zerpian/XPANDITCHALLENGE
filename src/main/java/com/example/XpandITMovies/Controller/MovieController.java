package com.example.XpandITMovies.Controller;

import java.util.List;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Model.Movies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public Movie postMovie(@RequestBody Movie movie) {
        return this.movieService.postMovie(movie);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return this.movieService.getMovie(id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteMovie(Long id) {
        this.movieService.deleteMovie(id);
    }
}
