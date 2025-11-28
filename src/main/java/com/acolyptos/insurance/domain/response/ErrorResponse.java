package com.acolyptos.insurance.domain.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  private final boolean success = false;
  private final int code;
  private final HttpStatus httpStatus;
  private final Map<String, String> errors;
  private final LocalDateTime timestamp = LocalDateTime.now();

  public ErrorResponse(
      final int code, final HttpStatus httpStatus, final Map<String, String> errors) {
    this.code = code;
    this.httpStatus = httpStatus;
    this.errors = errors;
  }

  public int getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public String getFormattedTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return timestamp.format(formatter);
  }

  @Override
  public String toString() {
    return "ErrorResponse {\n\tsuccess: "
        + success
        + ",\n\tcode: "
        + code
        + ",\n\thttpStatus: "
        + httpStatus
        + ",\n\terrors: "
        + errors
        + ",\n\ttimestamp: "
        + timestamp
        + "\n}";
  }
}
