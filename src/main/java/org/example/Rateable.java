package org.example;

/**
 * Author: Olfa
 * Date: 14/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */

@FunctionalInterface //you must have one abstarct method
public interface Rateable <T>{
    public static final Rating DEFAULT_RATING = Rating.NOT_STAR;
    T applyRating(Rating rating);

    public default T applyRating(int stars){
        return applyRating(convert(stars));
    }

    public default Rating getRating() {
        return DEFAULT_RATING;
    }

    public static Rating convert (int stars){
       return (stars >=0 && stars <= 5) ? Rating.values()[stars] : DEFAULT_RATING;

    }

}
