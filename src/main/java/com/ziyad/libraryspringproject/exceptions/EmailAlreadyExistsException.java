package com.ziyad.libraryspringproject.exceptions;

public class EmailAlreadyExistsException extends ResourceAlreadyExistsException{

    public EmailAlreadyExistsException(String message){
        super(message);

    }
}
