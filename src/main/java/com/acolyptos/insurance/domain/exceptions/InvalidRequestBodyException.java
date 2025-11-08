package com.acolyptos.insurance.domain.exceptions;

public class InvalidRequestBodyException extends RuntimeException {

  public InvalidRequestBodyException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
  }

  public InvalidRequestBodyException(String errorMessage) {
    super(errorMessage);
  }
}
