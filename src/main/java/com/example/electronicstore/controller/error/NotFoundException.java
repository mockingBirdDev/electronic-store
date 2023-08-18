package com.example.electronicstore.controller.error;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id, String resource) {
        super(("Resource \"").concat(resource).concat("\" not found, id: ").concat(String.valueOf(id)));
    }
}
