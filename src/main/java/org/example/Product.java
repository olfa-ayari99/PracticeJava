package org.example;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.example.Rating.NOT_STAR;

/**
 * Author: Olfa 
 * Date: 11/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Product {
    public static final BigDecimal DISCOUNT_PRICE= BigDecimal.valueOf(0.1);

    private int Id;
    private String Name;
    private BigDecimal Price;
    private Rating rating;


    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.Id = id;
        this.Name = name;
        this.Price = price;
        this.rating = rating;
    }

    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, NOT_STAR); // static import for rating so can juste write NOT_STAR without the rating
    }

    //to allow tho Shop to compile
    public Product() {
        this(0, "NO_NAME", BigDecimal.valueOf(0.1)); //p4

    }

    public Rating getRating() {
        return rating;
    }

    public int getId() {
        return Id;
    }

    public void setId(  int id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName( String name) {
        this.Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
       // price= BigDecimal.ONE;
        this.Price = price;
    }


    public BigDecimal getDiscount() {
        return Price.multiply(DISCOUNT_PRICE).setScale(2, HALF_UP);
    }

    public Product applyRating(Rating resultRating) {
        return new Product(this.Id, this.Name, this.Price, resultRating);
    }
}

