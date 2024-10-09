package com.example.XpandITMovies.Service;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Model.Movies.MovieModelAssembler;
import org.springframework.hateoas.CollectionModel;
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

    /**
     * Communicates with the repository to return all movies in the database.
     * Calls movieModelAssembler to wrap the whole list in the EntityModel class and returns it
     * @return the list of the movies wrapped in
     */
    public CollectionModel<EntityModel<Movie>> getAllMovies() {
        List<Movie> movies = repository.findAll();

        return movieModelAssembler.toCollectionModel(movies);
    }

    /**
     * Communicates with the repository to save data. It if it successful it
     * calls movieModelAssembler to wrap the movie in the EntityModel class and returns it,
     * otherwise it returns null.
     * @param movie
     * @return the movie saved wrapped in entity model
     */
    public EntityModel<Movie> postMovie(Movie movie) {
        Movie movieCreated = repository.save(movie);


        if(movieCreated.getId() != null){
            return movieModelAssembler.toModel(movieCreated);
        }else{
            return null;
        }
    }

    /**
     * Communicates with the repository to return a movie identified by its id.
     * If it is successful it will return the movie wrapped in EntityModel class,
     * otherwise it returns null
     * @param id movie id
     * @return
     */
    public EntityModel<Movie> getMovie(Long id) {

        Movie movie = repository.findById(id).orElse(null);
        if(movie != null) {
            return movieModelAssembler.toModel(movie);

        }else{
           return null;
        }


    }

    /**
     * Communicates with the repository to update the data in a movie, identified by its id, with
     * the new data from newMovie.
     * If its successful it will return the movie wrapped in EntityModel class,
     * otherwise it will return null
     * @param newMovie movie object containing the new data
     * @param id movie id of the record that will be updated
     * @return
     */
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

    /**
     * Communicates with the repository to delete a movie identified by its id. If its successful
     * it will return the movie wrapped in EntityModel class,
     * otherwise it will return null.
     * @param id
     * @return
     */
    public EntityModel<Movie> deleteMovie(Long id) {
        Movie movie=repository.findById(id).orElse(null);
        if(movie!=null) {
            repository.deleteById(id);
            return movieModelAssembler.toModel(movie);
        }else{
            return null;
        }

    }

    /**
     * Communicates with the repository to return all movies in the database that match with the launchDate provided.
     * Calls movieModelAssembler to wrap the whole list in the EntityModel class and returns it
     * @param launchDate
     * @return
     */
    public CollectionModel<EntityModel<Movie>> filterMovies(LocalDate launchDate){
        List<Movie> movies =repository.filterMovies(launchDate);

        return movieModelAssembler.toCollectionModel(movies);
    }
}
