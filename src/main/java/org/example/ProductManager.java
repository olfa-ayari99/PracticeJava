package org.example;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * Author: Olfa
 * Date: 13/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class ProductManager {


    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private Map<Product, List<Review>> products = new HashMap<>();


    //private Product product;
    //private Review[] reviews = new Review[5];




    public ProductManager(Locale locale) {
        this.locale = locale;
        resources=ResourceBundle.getBundle("resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    // subclass of Product
   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating, LocalDate bestBefore) {
       Product product =  new Food (Id , Name , Price , rating , bestBefore);
       products.putIfAbsent(product, new ArrayList<>());
       return product;
   }

   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating) {
       Product product = new Drink(Id , Name , Price , rating);
       products.putIfAbsent(product, new ArrayList<>());
       return product;
   }

    public Product reviewProduct(int Id, Rating rating, String comments){
        return reviewProduct(findProduct(Id), rating, comments);
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
     //   if (reviews[reviews.length - 1] == null) {
       //     reviews = Arrays.copyOf(reviews, reviews.length + 5);
       // }
        List<Review> reviews = products.get(product);
        products.replace(product, reviews);
        reviews.add(new Review(rating, comments));
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating().ordinal();
        }
       /* int sum = 0, i = 0;
        boolean reviewed = false;
        while (i < reviews.length && !reviewed) {
            if (reviews[i] == null) {
                reviews[i] = new Review(rating, comments);  // Initialize review with rating and comments
                reviewed = true;
            }
            if (reviews[i].getRating() != null) {  // Check for null rating
                sum += reviews[i].getRating().ordinal();
            }
            i++;
        }*/
        // Calculate and apply the average rating based on existing reviews
       /* if (i > 0) {  // Prevent division by zero
            this.product = product.applyRating(Rateable.convert(Math.round((float) sum / i)));
        }*/
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);
        return product;
    }

    public Product findProduct(int Id) {
        Product result = null;
        for (Product product : products.keySet()) {
            if (product.getId() == Id) {
                result = product;
                break;
            }
        }
        return result;
    }

    public void printProductReport(int Id){
        printProductReport(findProduct(Id));

    }


// Format the product report
public void printProductReport(Product product) {
    List<Review> reviews = products.get(product);
    StringBuilder txt = new StringBuilder();
    txt.append(MessageFormat.format(resources.getString("product"),
            product.getName(),
            moneyFormat.format(product.getPrice()),
            product.getRating().getStars(),
            dateFormat.format(product.getBestBefore())));
    txt.append('\n');
   // boolean noReviews = true;
    Collections.sort(reviews);
    for (Review review : reviews) {
        if (review == null) {
            break;  // Stop if reviews are null
        }
        txt.append(MessageFormat.format(resources.getString("review"),
                review.getRating().getStars(),
                review.getComments()));
        txt.append('\n');
       // noReviews = false;
    }
    if (reviews.isEmpty()) {
        txt.append(resources.getString("no.reviews"));
        txt.append('\n');
    }
    System.out.println(txt);
}

}

