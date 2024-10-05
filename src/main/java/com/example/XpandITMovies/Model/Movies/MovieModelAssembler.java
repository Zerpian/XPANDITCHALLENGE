package com.example.XpandITMovies.Model.Movies;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.XpandITMovies.Controller.MovieController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class MovieModelAssembler implements RepresentationModelAssembler<Movie, EntityModel<Movie>> {

    @Override
    public EntityModel<Movie> toModel(Movie movie) {
        return EntityModel.of(movie,
                linkTo(methodOn(MovieController.class).getMovieById(movie.getId())).withSelfRel(),
                linkTo(methodOn(MovieController.class).getAllMovies()).withRel("movies"));
    }
}
