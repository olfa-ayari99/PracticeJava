package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Author: Olfa 
 * Date: 12/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class Shop {

    public static void main(String[] args) {
     //   ProductManager pm = new ProductManager(Locale.UK);
        ProductManager pm = new ProductManager(Locale.UK);
        Product p1= pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
        pm.printProductReport();

        p1= pm.reviewProduct(p1, Rating.FOUR_STARS, "Nice hot cup of tea");
        p1= pm.reviewProduct(p1, Rating.TWO_STARS, "Rather weak  tea");

        p1= pm.reviewProduct(p1, Rating.FOUR_STARS, "Fine tea");

        p1= pm.reviewProduct(p1, Rating.FOUR_STARS, "Good tea");
        p1= pm.reviewProduct(p1, Rating.FIVE_STARS, "Perfect tea");
        p1= pm.reviewProduct(p1, Rating.THREE_STARS, "Just add some lemon");

        pm.printProductReport();

        /*Product p2=pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STARS);
        Product p3= pm.createProduct(103, "Cake", BigDecimal.valueOf(1.99), Rating.FIVE_STARS, LocalDate.now().plusDays(2));
        Product p4= pm.createProduct(105, "Cake", BigDecimal.valueOf(1.99), Rating.FIVE_STARS, LocalDate.now());
        Product p5= p3.applyRating(Rating.THREE_STARS);
        Product p6= pm.createProduct (104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STARS);
        Product p7= pm.createProduct (104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STARS, LocalDate.now().plusDays(2));
        Product p8 = p1.applyRating(Rating.FIVE_STARS);
        Product p9 = p1.applyRating(Rating.TWO_STARS); */

       // System.out.println(p6.equals(p7)); // p6 and p7 are two different objects in memeory
     // the equal compares references

       /* if(p3 instanceof Food){
            LocalDate bestBefore = ((Food)p3).getBestBefore();
        }*/
       // p3.getBestBefore();
       // p2.getBestBefore();




        //p3= p3.applyRating(Rating.THREE_STARS);
        //p1.setId(001);
        //p1.setName("p1");
        //p1.setPrice(BigDecimal.valueOf(1.99));
        //System.out.println(p1.getId()+ " " + p1.getName()+ " " + p1.getPrice());
       // System.out.println(p1.getId()+ " "+ p1.getName()+ " " + p1.getPrice()+ " " + p1.getDiscount() + " " + p1.getRating().getStars());
      //  System.out.println(p2.getId()+ " "+ p2.getName()+ " " + p2.getPrice()+ " " + p2.getDiscount()+ " " + p2.getRating().getStars());
     //   System.out.println(p3.getId()+ " "+ p3.getName()+ " " + p3.getPrice()+ " " + p3.getDiscount()+ " " + p3.getRating().getStars());
       // System.out.println(p4.getId()+ " "+ p4.getName()+ " " + p4.getPrice()+ " " + p4.getDiscount()+ " " + p4.getRating().getStars());
      //  System.out.println(p5.getId()+ " "+ p5.getName()+ " " + p5.getPrice()+ " " + p5.getDiscount()+ " " + p5.getRating().getStars());
/*
        System.out.println(p1);  // using println the object is passed to string
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);
        System.out.println(p9);
*/

    }
}
