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
       // ProductManager pm = new ProductManager(Locale.UK);
     //  ProductManager pm = new ProductManager();
     ProductManager pm = ProductManager.getInstance();
     //   pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
      // pm.printProductReport(101);
        pm.parseReview("101,4,Nice cup of Tea");
        pm.parseProduct("0, 101, Tea, 1.99,0,2019-09-19");

    //    pm.reviewProduct(62, Rating.FOUR_STARS, "Nice hot cup of tea");
     //   pm.reviewProduct(101, Rating.TWO_STARS, "Rather weak  tea");
       // pm.reviewProduct(101, Rating.FOUR_STARS, "Fine tea");
        //pm.reviewProduct(101, Rating.FOUR_STARS, "Good tea");
        //pm.reviewProduct(101, Rating.FIVE_STARS, "Perfect tea");
        //pm.reviewProduct(101, Rating.THREE_STARS, "Just add some lemon");
       pm.printProductReport(101, "en-GB");
       // pm.changeLocale("ru-RU");

  /*      pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
      pm.reviewProduct(102, Rating.THREE_STARS, "Coffee was ok");
       pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk ?");
         pm.reviewProduct(102, Rating.FIVE_STARS, "It's perfect with ten spoons of suger !");
       // pm.printProductReport(102);

       pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_STAR , LocalDate.now().plusDays(2));
         pm.reviewProduct(103, Rating.FIVE_STARS, "Very nice Cake");
        pm.reviewProduct(103, Rating.FOUR_STARS, "It's good but I've expected more chocolate");
     pm.reviewProduct(103, Rating.FIVE_STARS, "This cake was perfect !");
       //pm.printProductReport(103);

        pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_STAR , LocalDate.now());
      pm.reviewProduct(104, Rating.THREE_STARS, "Tasty !");
       pm.reviewProduct(104, Rating.FOUR_STARS, "OK");
       //pm.printProductReport(104);

       pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR , LocalDate.now());
       pm.reviewProduct(105, Rating.THREE_STARS, "Tasty !");
         pm.reviewProduct(105, Rating.FOUR_STARS, "OK");
        //pm.printProductReport(105);

       pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR , LocalDate.now().plusDays(3));
       pm.reviewProduct(106, Rating.TWO_STARS, "Too sweet");
      pm.reviewProduct(106, Rating.THREE_STARS, "Better than cookie");
       pm.reviewProduct(106, Rating.TWO_STARS, "Too bitter");
       pm.reviewProduct(106, Rating.ONE_STAR, "I don't get it !");
        pm.printProductReport(106);

        pm.printProducts(p -> p.getPrice().floatValue() < 2  ,(p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
        pm.getDiscounts().forEach(
                (rating,discount) -> System.out.println(rating +"\t"+ discount));
*/












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



        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_STAR);
        pm.reviewProduct(102, Rating.THREE_STARS, "Coffee was ok");
        pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk?");
        pm.reviewProduct(102, Rating.FIVE_STARS, "Perfect with ten spoons of sugar!");
        pm.printProductReport(102, "en-GB");

        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_STAR, LocalDate.now().plusDays(2));
        pm.reviewProduct(103, Rating.FIVE_STARS, "Very nice Cake");
        pm.reviewProduct(103, Rating.FOUR_STARS, "Good but could use more chocolate");
        pm.reviewProduct(103, Rating.FIVE_STARS, "Perfect cake!");
        pm.printProductReport(103,"en-GB");

        pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_STAR, LocalDate.now());
        pm.reviewProduct(104, Rating.THREE_STARS, "Tasty!");
        pm.reviewProduct(104, Rating.FOUR_STARS, "OK");
        pm.printProductReport(104,"en-GB");

        pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR, LocalDate.now());
        pm.reviewProduct(105, Rating.THREE_STARS, "Tasty!");
        pm.reviewProduct(105, Rating.FOUR_STARS, "OK");
        pm.printProductReport(105,"en-GB");

        pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_STAR, LocalDate.now().plusDays(3));
        pm.reviewProduct(106, Rating.TWO_STARS, "Too sweet");
        pm.reviewProduct(106, Rating.THREE_STARS, "Better than cookie");
        pm.reviewProduct(106, Rating.TWO_STARS, "Too bitter");
        pm.reviewProduct(106, Rating.ONE_STAR, "Not good at all");
        pm.printProductReport(106, "en-GB");

        // Sorting and filtering products with lambda
    //    pm.printProducts(p -> p.getPrice().floatValue() < 2, (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());

        // Print all discounts for ratings
     //   pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));


    }
}
