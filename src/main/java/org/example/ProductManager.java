package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
  //  private ResourceFormatter formatter;
    private final ResourceBundle config = ResourceBundle.getBundle("config");
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));
    //private Path repportsFolder = Path.of(config.getString("repports.folders"));
    //private Path dataFolder = Path.of(config.getString("data.folders"));
    //private Path tempFolder = Path.of(config.getString("temp.folders"));
    private final Path repportsFolder = Path.of("C:/Users/Olf.Ayari/PracticeJava/reports");
    private final Path dataFolder = Path.of("C:/Users/Olf.Ayari/PracticeJava/data");
    private final Path tempFolder = Path.of("C:/Users/Olf.Ayari/PracticeJava/temps");
    private static final ProductManager pm = new ProductManager();

    public static ProductManager getInstance() {
        return pm;
    }

    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "ru-RU", new ResourceFormatter(new Locale("ru","RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));
    private static final Logger logger= Logger.getLogger(ProductManager.class.getName());

    //private Product product;
    //private Review[] reviews = new Review[5];

    public ProductManager(Locale locale , String languageTag) {
        //  formatter = formatters.getOrDefault(locale.getLanguage(), formatters.get("en-GB"));
        ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));


    }

    public ProductManager() {
       // changeLocale(languageTag);
    Locale.getDefault();
    }

    private void createDirectoryIfNotExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating directory: " + path, e);
            }
        }
    }

    private void dumpData() {
        try {
            // Ensure tempFolder exists
            if (!Files.exists(tempFolder)) {
                Files.createDirectory(tempFolder);
            }

            // Create the path to the file where data will be saved
            Path path = tempFolder.resolve(MessageFormat.format(config.getString("product.data.format"), LocalDate.now()));

            // Try writing the products data to the file
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
                out.writeObject(products);  // Write products object to file
              //  products = new HashMap<>();  // Clear the products map after saving
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error writing products data", ex);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error creating temp folder", ex);
        }
    }

/*
    public void changeLocale(String languageTag) {
       // formatter = formatters.getOrDefault(languageTag, formatters.get("en.GB"));
        ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));

    }   */

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

    public void printProductReport(int Id , String languageTag){
        try{
            printProductReport(findProduct(Id), languageTag);
        } catch (ProductManagerException ex){
            logger.log(Level.INFO,ex.getMessage());
        }
    }


// Format the product report
public void printProductReport(Product product, String languageTag) {
    ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    List<Review> reviews = products.get(product);
    Collections.sort(reviews);
   // StringBuilder txt = new StringBuilder();
    Path productFile =  repportsFolder.resolve(MessageFormat.format(config.getString("repport.file"), product.getId()));

    createDirectoryIfNotExists(repportsFolder); // Ensure reports directory exists

    try (PrintWriter out = new PrintWriter(new OutputStreamWriter(Files.newOutputStream( productFile, StandardOpenOption.CREATE),
            "UTF-8"))){
        out.append(formatter.formatProduct(product)+ System.lineSeparator());
       // txt.append('\n');
        if (reviews.isEmpty()) {
            out.append(formatter.getText()).append("\n");
        } else {
            out.append(reviews.stream()
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
        //System.out.println(txt);  //instead of printing the repoort in the console we will print it in the file

    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

public void printProducts(Predicate<Product> filter , Comparator<Product> sorter , String languageTag) {
    ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));

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

private Product loadProduct(Path file){
        Product product = null;
    try {
        product= parseProduct(Files.lines(dataFolder.resolve(file), Charset.forName("UTF-8")).findFirst().orElseThrow());
    } catch (IOException e) {
        //Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE,e.getMessage(),e);
        logger.log(Level.SEVERE,"Error loading product",e.getMessage());
    }
    return product;

}

private List<Review> loadReviews(Product product) {
        List<Review> reviews = null;
        Path file= dataFolder.resolve(MessageFormat.format(config.getString("reviews.file"), product.getId()));
        if(!Files.exists(file)){
            reviews = new ArrayList<>();
        } else {
            try {
                reviews =  Files.lines(file, Charset.forName("UTF-8"))
                        .map(text -> parseReview(text))
                        .filter(review -> review != null)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.WARNING,"Error loading review" ,e.getMessage());
            }

        }
        return reviews;

}

public Review parseReview(String text) {
        Review review= null;
    try {
        Object[] values = reviewFormat.parse(text);
       // reviewProduct(Integer.parseInt((String)values[0]),Rateable.convert(Integer.parseInt((String)values[1])), (String)values[2]);
        review= new Review(Rateable.convert(Integer.parseInt((String)values[0])), (String)values[1]);
    } catch (ParseException ex) {
        logger.log(Level.WARNING,"Error parsing review "+text,ex);
    }
    return review;

}

/*public Product parseProduct(String text) {
        Product product= null;
        try {
            Object[] values = productFormat.parse(text);
            int Id = Integer.parseInt((String)values[1]);
            String Name = (String)values[2];
            BigDecimal Price = BigDecimal.valueOf(Double.parseDouble((String)values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String)values[4]));
            switch ((String) values[0]) {
                case "0":
                   product= new Drink(Id, Name, Price, rating);
                    break;
                    case "1":
                        LocalDate bestBefore = LocalDate.parse((String)values[5]);
                        product= new Food(Id, Name, Price, rating, bestBefore);
            }
        }catch (ParseException|NumberFormatException | DateTimeParseException ex){
            logger.log(Level.WARNING,"Error parsing review "+text,ex.getMessage());
        }
        return product;
}*/


    public Product parseProduct(String text) {
        Product product = null;
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt(((String) values[1]).trim());  // Trim spaces here
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rating.values()[Integer.parseInt(((String) values[4]).trim())]; // Trim spaces for rating

            switch ((String) values[0]) {
                case "0":
                    product = new Drink(id, name, price, rating);
                    break;
                case "1":
                    LocalDate bestBefore = LocalDate.parse((String) values[5]);
                    product = new Food(id, name, price, rating, bestBefore);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid product type");
            }
        } catch (ParseException | NumberFormatException | DateTimeParseException ex) {
            logger.log(Level.WARNING, "Error parsing product " + text, ex);
        }
        return product;
    }


    public  Map<String, String > getDiscounts(String languageTag) {
        ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
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

