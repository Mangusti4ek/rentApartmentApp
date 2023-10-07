package com.example._rent_apartment.application_exeption;

public class AuthException extends RuntimeException{

    public AuthException(String message) {
        super(message);
    }
}
