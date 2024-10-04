package com.example.XpandITMovies.Infrastructure.Movies;

import com.example.XpandITMovies.Model.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}