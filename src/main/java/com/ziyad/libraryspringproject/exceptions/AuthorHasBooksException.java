package com.ziyad.libraryspringproject.exceptions;

public class AuthorHasBooksException extends ResourceConflictException{

    public AuthorHasBooksException(String message){
        super(message);
    }
}
