package com.quick.aspect;

import com.quick.exception.RequestConflictException;
import com.quick.exception.ResourceNotFoundException;
import com.quick.model.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RequestConflictException.class})
    private ResponseEntity<ErrorResponse> handleExpenseExceptions(RequestConflictException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    private ResponseEntity<ErrorResponse> handleExpenseExceptions(ResourceNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<ErrorResponse> handleExpenseExceptions(Exception ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
