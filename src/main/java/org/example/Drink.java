package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Author: Olfa
 * Date: 12/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public final class Drink extends Product {
    // add final : that means that in the future nobody would be able to create subclasses of Drink

     Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);

    }

  @Override
    public Product applyRating(Rating newrating) {
       return new Drink(this.getId(), this.getName(), this.getPrice(), newrating);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();
        return (now.isAfter(LocalTime.of(17,30)) && now.isBefore(LocalTime.of(18,30)))
                ? super.getDiscount() : BigDecimal.ZERO;
    }



}
