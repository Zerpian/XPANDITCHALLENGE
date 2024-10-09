package com.example.XpandITMovies.Model.Movies;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Movie {

    private @Id @GeneratedValue Long id;
    private String title;
    private LocalDate launchDate;
    private int rank;
    private double revenue;

    /**
     * constructor for orm tool use
     */
    protected Movie() {}

    public Movie(String title, LocalDate launchDate, int rank, double revenue) {
        setTitle(title);
        setLaunchDate(launchDate);
        setRank(rank);
        setRevenue(revenue);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getRank() {
        return rank;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRevenue(double revenue) {
        this.revenue = checkRevenue(revenue);
    }

    public void setRank(int rank) {
        this.rank = checkRank(rank);
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    /**
     * ensures the rank cannot be higher than 10 or lower than 0
     * @param rank
     * @return rank or throws exception
     */
    private int checkRank(int rank){
        if(rank >= 0 && rank <=10 ){
            return rank;
        }else{
            throw new IllegalArgumentException("Rank must be between 0 and 10");
        }
    }

    /**
     * ensures the revenue cannot be a negative number
     * @param revenue
     * @return revenue or throws exception
     */
    private double checkRevenue(double revenue){
        if(revenue >= 0){
            return revenue;
        }else{
            throw new IllegalArgumentException("Revenue must be non-negative");
        }
    }
}