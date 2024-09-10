package com.example.demo.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super();
    }

    // Constructor that takes a message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
