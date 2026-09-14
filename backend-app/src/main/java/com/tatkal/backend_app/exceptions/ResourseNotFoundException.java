package com.tatkal.backend_app.exceptions;

public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException(String message){
        super(message);
    }
    public ResourseNotFoundException(){
        super("Resouse Not found");
    }
}
