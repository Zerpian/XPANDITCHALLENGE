package com.example.XpandITMovies.Model.Movies;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    private final MovieModelAssembler movieModelAssembler;

    public MovieService(MovieRepository repository, MovieModelAssembler movieModelAssembler) {
        this.repository = repository;
        this.movieModelAssembler = movieModelAssembler;
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
        }).orElseThrow(() ->
            new RuntimeException("Movie not found")

        );
    }

    public void deleteMovie(Long id) {
        repository.deleteById(id);
    }

    @Query("select a from Movie a where a.launchDate = :launchDate")
    public List<Movie> filterMovies(@Param("launchDate") LocalDate launchDate){
        return repository.filterMovies(launchDate);
    }
}
