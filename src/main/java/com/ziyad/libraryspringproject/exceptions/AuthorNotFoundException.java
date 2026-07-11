package com.ziyad.libraryspringproject.exceptions;




public class AuthorNotFoundException extends ResourceNotFoundException {


    public AuthorNotFoundException(String message){
        super(message);
    }


}
