package com.workintech.s18d1.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handlerException(BurgerException burgerException)
    {
        log.error("Burger API error: {}", burgerException.getMessage(), burgerException);
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(burgerException.getMessage());
        return new ResponseEntity<BurgerErrorResponse>(errorResponse, burgerException.getStatus());

    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> generalHandler(Exception exception) // for all cases
    {
        log.error("Unexpected error", exception);
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<BurgerErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
