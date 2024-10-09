package com.example.XpandITMovies.Infrastructure.Movies;

import com.example.XpandITMovies.Model.Movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select a from Movie a where a.launchDate = :launchDate")
    List<Movie> filterMovies(@Param("launchDate") LocalDate launchDate);

}