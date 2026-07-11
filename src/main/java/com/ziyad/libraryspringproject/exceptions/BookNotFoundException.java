package com.ziyad.libraryspringproject.exceptions;



public class BookNotFoundException extends ResourceNotFoundException {


    public BookNotFoundException(String message){
        super(message);
    }


}
