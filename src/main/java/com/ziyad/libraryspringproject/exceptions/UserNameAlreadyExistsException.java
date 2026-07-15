package com.ziyad.libraryspringproject.exceptions;

public class UserNameAlreadyExistsException extends ResourceAlreadyExistsException{

    public UserNameAlreadyExistsException(String message){
        super(message);
    }
}
