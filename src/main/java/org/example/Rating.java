package org.example;

/**
 * Author: Olfa
 * Date: 12/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public enum Rating {
    NOT_STAR("☆☆☆☆☆"),
    ONE_STAR("★☆☆☆☆"),
    TWO_STARS("★★☆☆☆"),
    THREE_STARS("★★★☆☆"),
    FOUR_STARS("★★★★☆"),
    FIVE_STARS("★★★★★");
// constructor

    public String getStars() {
        return stars;
    }

    private String stars;
    Rating(String stars) {
        this.stars = stars;

    }
}
