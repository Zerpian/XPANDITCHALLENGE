package com.example.XpandITMovies.Model.Movies;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAllMovies() {

        return repository.findAll();
    }

    public Movie postMovie(Movie movie) {
        return repository.save(movie);
    }

    public Movie getMovie(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found") );
    }

    public Movie putMovie(Movie newMovie, Long id) {
        return repository.findById(id).map(movie -> {
            movie.setTitle(newMovie.getTitle());
            movie.setLaunchDate(newMovie.getLaunchDate());
            movie.setRank(newMovie.getRank());
            movie.setRevenue(newMovie.getRevenue());
            return repository.save(movie);
        }).orElseGet(() -> {
            return repository.save(newMovie);
        });
    }

    public void deleteMovie(Long id) {
        repository.deleteById(id);
    }


}
