package com.example.XpandITMovies.Controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Model.Movies.MovieModelAssembler;
import com.example.XpandITMovies.Model.Movies.MovieService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MovieController {

    private final MovieService movieService;

    private final MovieModelAssembler movieModelAssembler;

    public MovieController(MovieService movieService, MovieModelAssembler movieModelAssembler) {
        this.movieService = movieService;
        this.movieModelAssembler = movieModelAssembler;
    }

    @PostMapping("/movies")
    public EntityModel<Movie> postMovie(@RequestBody Movie movie) {
        /*return EntityModel.of(this.movieService.postMovie(movie),
                linkTo(methodOn(MovieController.class).postMovie(movie)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
        return movieModelAssembler.toModel(this.movieService.postMovie(movie));
    }

    @GetMapping("/movies")
    public CollectionModel<EntityModel<Movie>> getAllMovies() {

        //return movieModelAssembler.toCollectionModel(movieService.getAllMovies());
        return CollectionModel.of(movieService.getAllMovies().stream()
                .map(movieModelAssembler::toModel).collect(Collectors.toList()),
                linkTo(methodOn(MovieController.class).getAllMovies()).withSelfRel());
    }

    @GetMapping("/movies/{id}")
    public EntityModel<Movie> getMovieById(@PathVariable Long id) {
        return movieModelAssembler.toModel(this.movieService.getMovie(id));
        /*return EntityModel.of(this.movieService.getMovie(id),
                linkTo(methodOn(MovieController.class).getMovieById(id)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        this.movieService.deleteMovie(id);
    }

    @PutMapping("/movies/{id}")
    public EntityModel<Movie> putMovie(@RequestBody Movie movie,@PathVariable Long id) {
        /*return EntityModel.of(this.movieService.putMovie(movie,id),
                linkTo(methodOn(MovieController.class).putMovie(movie, id)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
        return movieModelAssembler.toModel(this.movieService.putMovie(movie, id));
    }
    /*
    public void getFilteredMovies(){

    }*/
}
