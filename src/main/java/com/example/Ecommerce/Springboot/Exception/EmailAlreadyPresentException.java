package com.example.Ecommerce.Springboot.Exception;

public class EmailAlreadyPresentException extends Exception{
    public EmailAlreadyPresentException(String message){
        super(message);
    }
}
