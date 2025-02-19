package org.example;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Author: Olfa
 * Date: 13/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class ProductManager {




    private Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;
    private ResourceBundle config = ResourceBundle.getBundle("config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));
    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "ru-RU", new ResourceFormatter(new Locale("ru","RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));
    private static final Logger logger= Logger.getLogger(ProductManager.class.getName());

    //private Product product;
    //private Review[] reviews = new Review[5];

    public ProductManager(Locale locale) {

    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);

    }

    public void changeLocale(String languageTag) {
        formatter = formatters.getOrDefault(languageTag, formatters.get("en.GB"));
    }

    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
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
        try {
            return reviewProduct(findProduct(Id), rating, comments);
        } catch (ProductManagerException ex) {
                logger.log(Level.INFO ,ex.getMessage());
        }
        return null;
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
     //   if (reviews[reviews.length - 1] == null) {
       //     reviews = Arrays.copyOf(reviews, reviews.length + 5);
       // }
        List<Review> reviews = products.get(product);
        products.replace(product, reviews);
        reviews.add(new Review(rating, comments));

        product = product.applyRating(
                Rateable.convert(
                        (int) Math.round(
                                reviews.stream().mapToInt(r -> r.getRating().ordinal())
                                        .average().orElse(0))));


      //  int sum = 0;
       // for (Review review : reviews) {
        //    sum += review.getRating().ordinal();
        //}
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
        //product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.put(product, reviews);
        return product;
    }

    public Product findProduct(int Id) throws ProductManagerException {
     /*   Product result = null;
        for (Product product : products.keySet()) {
            if (product.getId() == Id) {
                result = product;
                break;
            }
        }
        return result;  */
        return products.keySet()
                .stream()
                .filter (p-> p.getId() == Id)
                .findFirst()
                .orElseThrow(() -> new ProductManagerException("Product with id " + Id + "not found")); //.get();
               // .orElse(null);
    }

    public void printProductReport(int Id){
        try{
            printProductReport(findProduct(Id));
        } catch (ProductManagerException ex){
            logger.log(Level.INFO,ex.getMessage());
        }
    }


// Format the product report
public void printProductReport(Product product) {
    List<Review> reviews = products.get(product);
    StringBuilder txt = new StringBuilder();
    txt.append( formatter.formatProduct(product)  );
    txt.append('\n');
    if(reviews.isEmpty()){
        txt.append(formatter.getText()).append("\n");
    }else {
        txt.append(reviews.stream()
                .map(r -> formatter.formatReview(r) + "\n")
                .collect(Collectors.joining()));
    }
   // boolean noReviews = true;
    //Collections.sort(reviews);
   /* for (Review review : reviews) {
        if (review == null) {
            break;  // Stop if reviews are null
        }
        txt.append( formatter.formatReview(review)  );
        txt.append('\n');
       // noReviews = false;
    }
    if (reviews.isEmpty()) {
        txt.append(formatter.getText("no.reviews"));
        txt.append('\n');
    } */
    System.out.println(txt);
}

public void printProducts(Predicate<Product> filter , Comparator<Product> sorter) {
     //   List<Product> productList= new ArrayList<>(products.keySet());
          //productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
        products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .forEach(p ->txt.append(formatter.formatProduct(p) + "\n" ));
        /*for(Product product : productList){
            txt.append(formatter.formatProduct(product));
            txt.append('\n');
        }*/
        System.out.println(txt);
}

public void parseReview(String text) {
    try {
        Object[] values = reviewFormat.parse(text);
        reviewProduct(Integer.parseInt((String)values[0]),Rateable.convert(Integer.parseInt((String)values[1])), (String)values[2]);
    } catch (ParseException ex) {
        logger.log(Level.WARNING,"Error parsing review "+text,ex);
    }

}

public  Map<String, String > getDiscounts() {
        return  products.keySet()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                product -> product.getRating().getStars(),
                                Collectors.collectingAndThen(
                                Collectors.summingDouble(
                                        product -> product.getDiscount().doubleValue()),
                                                discount -> formatter.moneyFormat.format(discount)
                        )
                        )
                );
}

private static class ResourceFormatter {
    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private ResourceFormatter(Locale locale) {
        this.locale = locale;
        resources=ResourceBundle.getBundle("resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    private String formatProduct(Product product) {
       return MessageFormat.format(resources.getString("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRating().getStars(),
                dateFormat.format(product.getBestBefore()));
    }

    private String formatReview(Review review) {
        return MessageFormat.format(resources.getString("review"),
                review.getRating().getStars(),
                review.getComments());

    }

    private String getText() {
        return resources.getString("no Reviews");
    }
}

}

