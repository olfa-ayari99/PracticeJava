package org.example;

/**
 * Author: Olfa
 * Date: 14/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Review {

    private Rating rating;
    private String comments;

    public Rating getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}
