package org.example;

/**
 * Author: Olfa
 * Date: 14/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Review {

    private Rating rating;

    public Review(Rating rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }

    private String comments;

    public Rating getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }


}
