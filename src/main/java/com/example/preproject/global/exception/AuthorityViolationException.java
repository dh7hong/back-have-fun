package com.example.preproject.exception;

public class AuthorityViolationException extends RuntimeException {

    public AuthorityViolationException(String msg) {
        super(msg);
    }
}