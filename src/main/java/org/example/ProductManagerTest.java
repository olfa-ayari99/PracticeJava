package org.example;

/**
 * Author: Olfa
 * Date: 20/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */


import java.math.BigDecimal;
import java.util.concurrent.*;
        import java.util.logging.*;

public class ProductManagerTest {
    // we are simulating concurrent tasks (tasks that happen at the same time) where multiple users (or threads) are:
    //Creating products.
    //Reviewing those products.
    //It logs what happens, and handles any errors that occur during the process.
    //At the end, it shuts down the executor and waits for all tasks to finish.


    private static final Logger logger = Logger.getLogger(ProductManagerTest.class.getName());
    private static final ProductManager productManager = ProductManager.getInstance();  // Singleton instance of ProductManager

    public static void main(String[] args) {
        // Create an ExecutorService to run tasks concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(2);  // We use 2 threads for simplicity

        // Task 1: Simulate creating a product
        executorService.submit(() -> createProduct(1, "Product 1", BigDecimal.valueOf(99.99), Rating.TWO_STARS));

        // Task 2: Simulate creating another product
        executorService.submit(() -> createProduct(2, "Product 2", BigDecimal.valueOf(149.99), Rating.FIVE_STARS));

        // Task 3: Simulate reviewing the first product
        executorService.submit(() -> reviewProduct(1, Rating.THREE_STARS, "Good product"));

        // Task 4: Simulate reviewing the second product
        executorService.submit(() -> reviewProduct(2, Rating.FIVE_STARS, "Excellent product"));

        // Once all tasks are submitted, we shut down the executor service
        shutdownExecutorService(executorService);
    }

    // Helper method to create a product
    private static void createProduct(int productId, String name, BigDecimal price, Rating rating) {
        try {
            // Create a new product using the given parameters
            Product product = productManager.createProduct(productId, name, price, rating);
            logger.log(Level.INFO, "Created product: " + product.getName());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating product with ID: " + productId, e);
        }
    }

    // Helper method to review a product
    private static void reviewProduct(int productId, Rating rating, String comment) {
        try {
            // Find the product by ID and then add a review
            Product product = productManager.findProduct(productId);
            productManager.reviewProduct(product, rating, comment);
            logger.log(Level.INFO, "Reviewed product: " + product.getName());
        } catch (ProductManagerException e) {
            logger.log(Level.SEVERE, "Error reviewing product with ID: " + productId, e);
        }
    }

    // Method to properly shut down the ExecutorService after tasks are finished
    private static void shutdownExecutorService(ExecutorService executorService) {
        executorService.shutdown();  // Initiates an orderly shutdown of the executor
        try {
            // Wait for all tasks to finish before completely shutting down
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();  // Forcefully shutdown if tasks take too long
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();  // Shutdown now if interrupted
        }
    }
}

