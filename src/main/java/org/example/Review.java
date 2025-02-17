package org.example;

/**
 * Author: Olfa
 * Date: 14/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Review implements Comparable<Review>{

    private Rating rating;
    private String comments;

    public Review(Rating rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }



    public Rating getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }



    @Override
    public int compareTo(Review other) {
        return other.rating.ordinal() - this.rating.ordinal(); // in ther order of their ratings
    }
}
