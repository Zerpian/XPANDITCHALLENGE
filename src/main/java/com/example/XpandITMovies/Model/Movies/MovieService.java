package com.example.XpandITMovies.Model.Movies;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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

    public CollectionModel<EntityModel<Movie>> getAllMovies() {
        List<Movie> movies = repository.findAll();

        return movieModelAssembler.toCollectionModel(movies);
    }

    public EntityModel<Movie> postMovie(Movie movie) {
        Movie movieCreated = repository.save(movie);


        if(movieCreated.getId() != null){
            return movieModelAssembler.toModel(movieCreated);
        }else{
            return null;
        }
    }

    public EntityModel<Movie> getMovie(Long id) {

        Movie movie = repository.findById(id).orElse(null);
        if(movie != null) {
            return movieModelAssembler.toModel(movie);

        }else{
           return null;
        }


    }

    public EntityModel<Movie> putMovie(Movie newMovie, Long id) {
        Movie movieUpdated=repository.findById(id).map(movie -> {
            movie.setTitle(newMovie.getTitle());
            movie.setLaunchDate(newMovie.getLaunchDate());
            movie.setRank(newMovie.getRank());
            movie.setRevenue(newMovie.getRevenue());
            return repository.save(movie);
        }).orElse(null);



        if(movieUpdated!=null) {
            return movieModelAssembler.toModel(movieUpdated);
        }else{
            return null;
        }
    }

    public void deleteMovie(Long id) {
        repository.deleteById(id);
    }

    @Query("select a from Movie a where a.launchDate = :launchDate")
    public CollectionModel<EntityModel<Movie>> filterMovies(@Param("launchDate") LocalDate launchDate){
        List<Movie> movies =repository.filterMovies(launchDate);

        return movieModelAssembler.toCollectionModel(movies);
    }
}
