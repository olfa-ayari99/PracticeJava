package org.example;

/**
 * Author: Olfa
 * Date: 19/02/2025
 * Olf.Ayari
 * Version: 1.0-SNAPSHOT
 */
public class ProductManagerException extends Exception {

    public ProductManagerException() {
        super ();
    }
    public ProductManagerException(String message) {
        super(message);
    }

    public ProductManagerException(String message, Throwable cause) {
        super(message, cause);
    }

}
