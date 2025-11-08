package com.acolyptos.insurance.domain.exceptions;

public class EntityDoesNotExistException extends RuntimeException {

  public EntityDoesNotExistException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
  }

  public EntityDoesNotExistException(String errorMessage) {
    super(errorMessage);
  }
}
