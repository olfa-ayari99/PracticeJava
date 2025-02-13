package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Author: Olfa
 * Date: 13/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class ProductManager {

    // subclass of Product
   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating, LocalDate bestBefore) {
       return  new Food (Id , Name , Price , rating , bestBefore);
   }

   public Product createProduct(int Id , String Name , BigDecimal Price , Rating rating) {
       return new Drink(Id , Name , Price , rating);
   }
}
