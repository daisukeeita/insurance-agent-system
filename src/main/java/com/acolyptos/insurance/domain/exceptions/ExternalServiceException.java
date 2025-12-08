package com.acolyptos.insurance.domain.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends RuntimeException {
  private final HttpStatus httpStatus;
  private final String responseBody;

  public ExternalServiceException(String errorMessage, HttpStatus httpStatus, String responseBody) {
    super(errorMessage);
    this.httpStatus = httpStatus;
    this.responseBody = responseBody;
  }

  public ExternalServiceException(
      String errorMessage, Throwable cause, HttpStatus httpStatus, String responseBody) {
    super(errorMessage, cause);
    this.httpStatus = httpStatus;
    this.responseBody = responseBody;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getResponseBody() {
    return responseBody;
  }
}
