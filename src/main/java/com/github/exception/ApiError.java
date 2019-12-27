package com.github.exception;

public class ApiError extends RuntimeException {

  private String message;

  public ApiError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
