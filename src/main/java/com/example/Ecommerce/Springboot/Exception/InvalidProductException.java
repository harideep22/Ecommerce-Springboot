package com.example.Ecommerce.Springboot.Exception;

public class InvalidProductException extends Exception{
    public InvalidProductException(String message){
        super(message);
    }
}
