package com.portfolio.quizapp.exception_handling.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(int id) {
    super("User not found with id" + id);
}

}