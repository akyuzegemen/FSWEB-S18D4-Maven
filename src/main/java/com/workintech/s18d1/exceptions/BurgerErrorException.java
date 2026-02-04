package com.workintech.s18d1.exceptions;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Getter
public class BurgerErrorException extends RuntimeException {
    private final HttpStatus status;


    public BurgerErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}
