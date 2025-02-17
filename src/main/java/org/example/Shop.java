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
      pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
        pm.printProductReport(101);

        pm.reviewProduct(101, Rating.FOUR_STARS, "Nice hot cup of tea");
    pm.reviewProduct(101, Rating.TWO_STARS, "Rather weak  tea");

        pm.reviewProduct(101, Rating.FOUR_STARS, "Fine tea");

         pm.reviewProduct(101, Rating.FOUR_STARS, "Good tea");
        pm.reviewProduct(101, Rating.FIVE_STARS, "Perfect tea");
       pm.reviewProduct(101, Rating.THREE_STARS, "Just add some lemon");

        pm.printProductReport(101);

        Product p2= pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
        p2= pm.reviewProduct(p2, Rating.THREE_STARS, "Coffee was ok");
        p2= pm.reviewProduct(p2, Rating.ONE_STAR, "Where is the milk ?");
        p2= pm.reviewProduct(p2, Rating.FIVE_STARS, "It's perfect with ten spoons of suger !");
        pm.printProductReport(p2);

        Product p3= pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_STAR , LocalDate.now().plusDays(2));
        p3= pm.reviewProduct(p3, Rating.FIVE_STARS, "Very nice Cake");
        p3= pm.reviewProduct(p3, Rating.FOUR_STARS, "It's good but I've expected more chocolate");
        p3= pm.reviewProduct(p3, Rating.FIVE_STARS, "This cake was perfect !");
        pm.printProductReport(p3);

        Product p4= pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_STAR , LocalDate.now());
        p4= pm.reviewProduct(p4, Rating.THREE_STARS, "Tasty !");
        p4= pm.reviewProduct(p4, Rating.FOUR_STARS, "OK");
        pm.printProductReport(p4);

        Product p5= pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR , LocalDate.now());
        p5= pm.reviewProduct(p5, Rating.THREE_STARS, "Tasty !");
        p5= pm.reviewProduct(p5, Rating.FOUR_STARS, "OK");
        pm.printProductReport(p5);

        Product p6= pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR , LocalDate.now().plusDays(3));
        p6= pm.reviewProduct(p6, Rating.TWO_STARS, "Too sweet");
        p6= pm.reviewProduct(p6, Rating.THREE_STARS, "Better than cookie");
        p6= pm.reviewProduct(p6, Rating.TWO_STARS, "Too bitter");
        p6= pm.reviewProduct(p6, Rating.ONE_STAR, "I don't get it !");
        pm.printProductReport(p6);













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
