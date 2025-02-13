package org.example;

import java.math.BigDecimal;
import java.util.Objects;

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



    // constructor with no paramaters   to allow tho Shop to compile
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

      /*@Override
  public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Id == product.Id && Objects.equals(Name, product.Name);
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
       if (obj != null && getClass() == obj.getClass()) {
            if (obj instanceof Product) {
                final Product other = (Product) obj;
                return this.Id == other.Id && Objects.equals(this.Name, other.Name);
            }
        }
        return false;
    }




    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.Id;
        return hash;
    }

    @Override
    public String toString() {
        return Id + " " + Name + " " + Price + " " + getDiscount()+ " " + rating.getStars();
    }

}

