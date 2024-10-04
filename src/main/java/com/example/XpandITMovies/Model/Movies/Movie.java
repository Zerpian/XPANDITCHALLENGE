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

    protected Movie() {}

    public Movie(String title, LocalDate launchDate, int rank, double revenue) {
        this.title = title;
        this.launchDate = launchDate;
        this.rank = rank;
        this.revenue = revenue;
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
        this.revenue = revenue;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }
}