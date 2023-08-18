package com.example.electronicstore.controller.error;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(Long id, String resource) {
        super(("Resource \"").concat(resource).concat("\" already exists, id: ").concat(String.valueOf(id)));
    }
}
