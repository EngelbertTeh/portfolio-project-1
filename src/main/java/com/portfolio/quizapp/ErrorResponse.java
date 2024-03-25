package com.portfolio.quizapp;

import lombok.Data;

@Data
public class ErrorResponse{
    int statusCode;
    String message;
  public ErrorResponse(int statusCode,String message) {
    this.statusCode = statusCode;
    this.message = message;  
  }
  
}
