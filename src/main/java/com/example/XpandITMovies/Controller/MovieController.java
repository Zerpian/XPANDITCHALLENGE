package com.example.XpandITMovies.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Model.Movies.MovieModelAssembler;
import com.example.XpandITMovies.Model.Movies.MovieService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> postMovie(@RequestBody Movie movie) {
        /*return EntityModel.of(this.movieService.postMovie(movie),
                linkTo(methodOn(MovieController.class).postMovie(movie)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
        //return movieModelAssembler.toModel(this.movieService.postMovie(movie));
        return ResponseEntity.created(movieModelAssembler.toModel(this.movieService.postMovie(movie))
                .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(movieModelAssembler.toModel(this.movieService.postMovie(movie)));
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {

        //return movieModelAssembler.toCollectionModel(movieService.getAllMovies());
        /*return CollectionModel.of(movieService.getAllMovies().stream()
                .map(movieModelAssembler::toModel).collect(Collectors.toList()),
                linkTo(methodOn(MovieController.class).getAllMovies()).withSelfRel());*/

        /*return ResponseEntity.ok(
                movieModelAssembler.toCollectionModel(this.movieService.getAllMovies())
                        .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(movieModelAssembler.toCollectionModel(this.movieService.getAllMovies()));*/
        return ResponseEntity.ok(movieModelAssembler.toCollectionModel(this.movieService.getAllMovies()));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        //return movieModelAssembler.toModel(this.movieService.getMovie(id));
        /*return EntityModel.of(this.movieService.getMovie(id),
                linkTo(methodOn(MovieController.class).getMovieById(id)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
        /*return ResponseEntity.created(movieModelAssembler.toModel(this.movieService.getMovie(id))
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(movieModelAssembler.toModel(this.movieService.getMovie(id)));*/
        return ResponseEntity.ok(movieModelAssembler.toModel(this.movieService.getMovie(id)));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        this.movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> putMovie(@RequestBody Movie movie,@PathVariable Long id) {
        /*return EntityModel.of(this.movieService.putMovie(movie,id),
                linkTo(methodOn(MovieController.class).putMovie(movie, id)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));*/
        //return movieModelAssembler.toModel(this.movieService.putMovie(movie, id));
        return ResponseEntity.created(movieModelAssembler.toModel(this.movieService.putMovie(movie, id))
                .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(movieModelAssembler.toModel(this.movieService.putMovie(movie, id)));
    }

    @GetMapping("/movies/filter/{launchDate}")
    public ResponseEntity<?> getFilteredMovies(@PathVariable LocalDate launchDate){
        return ResponseEntity.ok(movieModelAssembler.toCollectionModel(this.movieService.filterMovies(launchDate)));
    }
}
