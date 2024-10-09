package com.example.XpandITMovies.Controller;

import java.time.LocalDate;

import com.example.XpandITMovies.Model.Movies.Movie;
import com.example.XpandITMovies.Service.MovieService;
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

    /**
     * Communicates with service layer to save data into the repository, the service will try
     *  to return the data wrapped in entity layer class. If the return value is null the controller returns code 404,
     * otherwise returns code 201
     * @param movie
     * @return
     */
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

    /**
     * Communicates with service layer to get the data in the repository, the service will try
     * to return the data wrapped in entity layer class. It returns code 200
     * @return
     */
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {



        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    /**
     * Communicates with service layer to get the data of a single movie in the repository, the service will try
     * to return the data wrapped in entity layer class. The controller returns code 201, otherwise it returns code 404
     * @param id movie id
     * @return
     */
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

    /**
     * Communicates with the service layer to try to delete a record from the database, if it is successful
     * it returns code 204, otherwise it returns code 404
     * @param id
     * @return
     */
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {

        EntityModel<Movie> emMovie=this.movieService.deleteMovie(id);
        if(emMovie!=null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Communicates with the service layer to try to update a record on the database, if it is successful
     * the controller returns 201, otherwise returns 404
     * @param movie
     * @param id
     * @return
     */
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

    /**
     * Communicates with the service layer to return a list of the movies that launchdate is equal to the date given
     * in the request
     * @param launchDate
     * @return
     */
    @GetMapping("/movies/filter/{launchDate}")
    public ResponseEntity<?> getFilteredMovies(@PathVariable LocalDate launchDate){
        return ResponseEntity.ok(this.movieService.filterMovies(launchDate));
    }
}
