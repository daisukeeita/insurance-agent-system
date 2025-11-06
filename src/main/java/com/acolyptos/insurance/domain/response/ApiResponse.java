package com.acolyptos.insurance.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

  @NotNull(message = "HTTP Status is required for the response.")
  private HttpStatus httpStatus;

  private boolean success;

  @NotBlank(message = "Summary or message is required for the response.")
  private String message;

  private T data;

  private List<ApiError> errors;

  private LocalDateTime timestamp = LocalDateTime.now();

  /** Protecting the default constructor. */
  protected ApiResponse() {}

  /** Constructor for successful api response. */
  public ApiResponse(HttpStatus httpStatus, String message, T data) {
    this.httpStatus = httpStatus;
    this.success = true;
    this.message = message;
    this.data = data;
    this.errors = null;
    this.timestamp = LocalDateTime.now();
  }

  /** Constructor for unsuccessful api response. */
  public ApiResponse(HttpStatus httpStatus, String message, List<ApiError> errors) {
    this.httpStatus = httpStatus;
    this.success = false;
    this.message = message;
    this.data = null;
    this.errors = errors;
    this.timestamp = LocalDateTime.now();
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public boolean getSuccessStatus() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public List<ApiError> getErrors() {
    return errors;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "ApiResponse {\n\thttpStatus: "
        + httpStatus
        + ",\n\tsuccess: "
        + success
        + ",\n\tmessage: "
        + message
        + ",\n\tdata: "
        + data
        + ",\n\terrors: "
        + errors
        + ",\n\ttimestamp: "
        + timestamp
        + "\n}";
  }
}
