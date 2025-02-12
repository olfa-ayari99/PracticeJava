package org.example;

import java.math.BigDecimal;

/**
 * Author: Olfa 
 * Date: 12/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Shop {

    public static void main(String[] args) {
        Product p1= new Product(001, "Tea", BigDecimal.valueOf(1.99));
        Product p2= new Product(002, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STARS);
        Product p3= new Product(003, "Cake", BigDecimal.valueOf(1.99), Rating.FIVE_STARS);
        Product p4= new Product();
        Product p5= p3.applyRating(Rating.THREE_STARS);
        //p3= p3.applyRating(Rating.THREE_STARS);
        //p1.setId(001);
        //p1.setName("p1");
        //p1.setPrice(BigDecimal.valueOf(1.99));
        //System.out.println(p1.getId()+ " " + p1.getName()+ " " + p1.getPrice());
        System.out.println(p1.getId()+ " "+ p1.getName()+ " " + p1.getPrice()+ " " + p1.getDiscount() + " " + p1.getRating().getStars());
        System.out.println(p2.getId()+ " "+ p2.getName()+ " " + p2.getPrice()+ " " + p2.getDiscount()+ " " + p2.getRating().getStars());
        System.out.println(p3.getId()+ " "+ p3.getName()+ " " + p3.getPrice()+ " " + p3.getDiscount()+ " " + p3.getRating().getStars());
        System.out.println(p4.getId()+ " "+ p4.getName()+ " " + p4.getPrice()+ " " + p4.getDiscount()+ " " + p4.getRating().getStars());
        System.out.println(p5.getId()+ " "+ p5.getName()+ " " + p5.getPrice()+ " " + p5.getDiscount()+ " " + p5.getRating().getStars());



    }
}
