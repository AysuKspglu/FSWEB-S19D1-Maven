package com.workintech.s18d2.exceptions;

public class NegativeIdException extends RuntimeException {
    public NegativeIdException(Long id) {
        super("Id must be greater than 0. Given: " + id);
    }
}

