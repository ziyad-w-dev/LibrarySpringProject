package com.ziyad.libraryspringproject.exceptions;

public class UnauthorizedActionException extends RuntimeException{

    public UnauthorizedActionException(String meesage){
        super(meesage);
    }
}
