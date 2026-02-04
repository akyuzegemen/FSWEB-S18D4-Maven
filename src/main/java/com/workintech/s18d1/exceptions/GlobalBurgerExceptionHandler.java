package com.workintech.s18d1.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalBurgerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handlerException(BurgerErrorException burgerErrorException)
    {
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(burgerErrorException.getMessage());
        return new ResponseEntity<BurgerErrorResponse>(errorResponse, burgerErrorException.getStatus());

    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> generalHandler(Exception exception) // for all cases
    {
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<BurgerErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
