package com.example.XpandITMovies.Model.Movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie movie;

    @BeforeEach
    public void setUp(){
        movie=new Movie("Zombieland", LocalDate.of(2000,01,01),8,200.0);
    }

    @Test
    void setRevenue() {
        movie.setRevenue(10.0);

        assertEquals(10.0,movie.getRevenue());
    }

    @Test
    void setRevenueError(){


        assertThrows(IllegalArgumentException.class, ()-> {
            movie.setRevenue(-1.0);
        }, "Revenue must be non-negative");
    }

    @Test
    void setRank() {
        movie.setRank(7);

        assertEquals(7,movie.getRank());
    }

    @Test
    void setRankError(){


        assertThrows(IllegalArgumentException.class, ()-> {
            movie.setRank(15);
        }, "Rank must be between 0 and 10");
    }
}