package com.portfolio.quizapp.exception_handling.exceptions;

public class ContentNotFoundException extends RuntimeException {
  public ContentNotFoundException() {
    super("Table is missing, consider checking the database.");
  }
}
