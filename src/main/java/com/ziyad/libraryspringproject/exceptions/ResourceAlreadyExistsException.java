package com.ziyad.libraryspringproject.exceptions;

public class ResourceAlreadyExistsException extends ResourceConflictException {

    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
