package org.example;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

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

    private Product product;
    private Review[] reviews = new Review[5];




    public ProductManager(Locale locale) {
        this.locale = locale;
        resources=ResourceBundle.getBundle("resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    // subclass of Product
   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating, LocalDate bestBefore) {
       product =  new Food (Id , Name , Price , rating , bestBefore);
       return product;
   }

   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating) {
       product = new Drink(Id , Name , Price , rating);
       return product;
   }

    public Product reviewProduct(Product product, Rating rating, String comments) {
        if (reviews[reviews.length - 1] == null) {
            reviews = Arrays.copyOf(reviews, reviews.length + 5);
        }
        int sum = 0, i = 0;
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
        }
        // Calculate and apply the average rating based on existing reviews
        if (i > 0) {  // Prevent division by zero
            this.product = product.applyRating(Rateable.convert(Math.round((float) sum / i)));
        }
        return this.product;
    }

// Format the product report
public void printProductReport() {
    StringBuilder txt = new StringBuilder();
    txt.append(MessageFormat.format(resources.getString("product"),
            product.getName(),
            moneyFormat.format(product.getPrice()),
            product.getRating().getStars(),
            dateFormat.format(product.getBestBefore())));
    txt.append('\n');
    boolean noReviews = true;
    for (Review review : reviews) {
        if (review == null) {
            break;  // Stop if reviews are null
        }
        txt.append(MessageFormat.format(resources.getString("review"),
                review.getRating().getStars(),
                review.getComments()));
        txt.append('\n');
        noReviews = false;
    }
    if (noReviews) {
        txt.append(resources.getString("no.reviews"));
        txt.append('\n');
    }
    System.out.println(txt);
}

}

