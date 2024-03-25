package com.portfolio.quizapp;

import java.util.NoSuchElementException;

import org.hibernate.exception.DataException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.portfolio.quizapp.exceptions.ContentNotFoundException;
import com.portfolio.quizapp.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler { @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
     log.error(ex.getMessage());
      return ResponseEntity.ok("Not Found");
  }



  @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
  public ResponseEntity<String> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {
    log.error(ex.getMessage());
    return ResponseEntity.internalServerError().body("The contents you are currently looking for are not available. Please try again later.");
  }

  @ExceptionHandler(ContentNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleContentNotFoundException(ContentNotFoundException ex) {
    log.error(ex.getMessage());
    ErrorResponse errorResponse;
     errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }


  @ExceptionHandler({NoSuchElementException.class })
  public ResponseEntity<String> handleNoSuchElementException(Exception ex) {
    log.error(ex.getMessage());
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler({IllegalArgumentException.class, TypeMismatchException.class, NoResourceFoundException.class,HttpMessageNotReadableException.class,
    DataException.class, HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<String> handleInvalidRequestException(Exception ex) {
    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body("The request you have made is invalid. Please try again.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleAllOtherExceptions(Exception ex) {
    log.error(ex.getMessage());
    return ResponseEntity.internalServerError().body("There seems to be an issue with the server. Please try again later.");
  }


  
  
}
