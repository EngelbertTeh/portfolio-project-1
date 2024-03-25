package com.portfolio.quizapp.exceptions;

public class MissingParametersException extends RuntimeException {
  public MissingParametersException() {
    super("Missing parameters in request body. Please provide all required parameters.");
  }
  
}
