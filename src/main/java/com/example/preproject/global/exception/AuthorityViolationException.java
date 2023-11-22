package com.example.preproject.global.exception;

public class AuthorityViolationException extends RuntimeException {

    public AuthorityViolationException(String msg) {
        super(msg);
    }
}