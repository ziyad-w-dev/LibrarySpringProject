package com.ziyad.libraryspringproject.exceptions;


public class UserNotFoundException extends ResourceNotFoundException {


    public UserNotFoundException(String message){
        super(message);
    }
}
