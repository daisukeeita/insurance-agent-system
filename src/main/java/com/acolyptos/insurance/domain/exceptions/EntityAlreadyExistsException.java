package com.acolyptos.insurance.domain.exceptions;


public class EntityAlreadyExistsException extends RuntimeException {

  public EntityAlreadyExistsException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
  }

  public EntityAlreadyExistsException(String errorMessage) {
    super(errorMessage);
  }
}
