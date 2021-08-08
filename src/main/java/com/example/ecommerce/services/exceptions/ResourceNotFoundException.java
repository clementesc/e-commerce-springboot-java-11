package com.example.ecommerce.services.exceptions;

import javassist.NotFoundException;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " + id);
    }
}
