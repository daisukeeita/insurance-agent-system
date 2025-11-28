package com.acolyptos.insurance.domain.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;

public class SuccessResponse<T> {

  private final boolean success = true;
  private final int code;
  private final HttpStatus httpStatus;
  private final String message;
  private final T data;
  private final LocalDateTime timestamp = LocalDateTime.now();

  public SuccessResponse(
      final int code, final HttpStatus httpStatus, final String message, final T data) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.message = message;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  // public LocalDateTime getTimestamp() {
  //   return timestamp;
  // }

  public String getFormattedTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return timestamp.format(formatter);
  }

  @Override
  public String toString() {
    return "SuccessResponse {\n\tsuccess: "
        + success
        + ",\n\tcode: "
        + code
        + ",\n\thttpStatus: "
        + httpStatus
        + ",\n\tmessage: "
        + message
        + ",\n\tdata: "
        + data
        + ",\n\ttimestamp: "
        + timestamp
        + "\n}";
  }
}
