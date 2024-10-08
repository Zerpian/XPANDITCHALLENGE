package com.example.XpandITMovies.Controller;

import java.time.LocalDate;

import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Model.Movies.MovieModelAssembler;
import com.example.XpandITMovies.Model.Movies.MovieService;
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


@RestController
public class MovieController {

    private final MovieService movieService;



    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    @PostMapping("/movies")
    public ResponseEntity<?> postMovie(@RequestBody Movie movie) {

        EntityModel<Movie> emMovie=this.movieService.postMovie(movie);

        if(emMovie!=null){

        return ResponseEntity.created(emMovie
                .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(emMovie);
    }else{
        return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {



        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {


        EntityModel<Movie> emMovie=this.movieService.getMovie(id);
        if ( emMovie!= null) {
            return ResponseEntity.created(emMovie
                            .getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(emMovie);

        }else{
            return ResponseEntity.notFound().build();
        }



    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        this.movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> putMovie(@RequestBody Movie movie,@PathVariable Long id) {


        EntityModel<Movie> emMovie=this.movieService.putMovie(movie, id);

        if(emMovie != null) {
            return ResponseEntity.created(emMovie
                    .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(emMovie);
        }else{
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/movies/filter/{launchDate}")
    public ResponseEntity<?> getFilteredMovies(@PathVariable LocalDate launchDate){
        return ResponseEntity.ok(this.movieService.filterMovies(launchDate));
    }
}
