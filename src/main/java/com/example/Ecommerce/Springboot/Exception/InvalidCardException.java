package com.example.Ecommerce.Springboot.Exception;

public class InvalidCardException extends Exception{
    public InvalidCardException(String message){
        super(message);
    }
}
