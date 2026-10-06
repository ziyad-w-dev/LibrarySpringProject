package com.ziyad.libraryspringproject.exceptions;

public class UnauthorizedActionException extends RuntimeException{

    public UnauthorizedActionException(String message){
        super(message);
    }
}
