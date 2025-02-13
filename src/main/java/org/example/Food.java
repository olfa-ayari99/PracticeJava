package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author: Olfa
 * Date: 12/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public final class Food extends Product {
// add final : that means that in the future nobody would be able to create subclasses od Food
    private LocalDate bestBefore;

     Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }
// Get the value of the best before date for the product

    //Return the value of bestBefore
    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public String toString() {
        return super.toString() + " Best Before: " + bestBefore;
    }

    @Override
    public BigDecimal getDiscount() {
        return (bestBefore.isEqual(LocalDate.now()))
                ? super.getDiscount() : BigDecimal.ZERO;
    }


    @Override
    public Product applyRating(Rating newrating) {
        return new Food(getId(), getName(), getPrice(), newrating, bestBefore);
    }
}
