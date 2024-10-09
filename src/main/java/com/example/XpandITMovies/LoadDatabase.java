package com.example.XpandITMovies;

import com.example.XpandITMovies.Infrastructure.Movies.MovieRepository;
import com.example.XpandITMovies.Model.Movies.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MovieRepository repository) {
        /**
         * database starts with some default records
         */
        return args -> {
            log.info("Preloading " + repository.save(new Movie("Star Wars",LocalDate.parse("2000-08-01"), 7, 2000.00)));
            log.info("Preloading " + repository.save(new Movie("Rambo", LocalDate.parse("2000-01-01"), 8, 3000.00)));
        };
    }

}