package com.github.exception;

import com.github.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllException(final Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setError(true);
    errorResponse.setMessage("Something went wrong, Please try again");
    return new ResponseEntity<>(errorResponse, HttpStatus.OK);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ApiError.class)
  public ResponseEntity<ErrorResponse> handleApiException(final ApiError ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setError(true);
    errorResponse.setMessage(
        new StringBuilder("Error message: ").append(ex.getMessage()).toString());
    return new ResponseEntity<>(errorResponse, HttpStatus.OK);
  }
}
