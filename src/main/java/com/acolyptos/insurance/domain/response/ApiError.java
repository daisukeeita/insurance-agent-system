package com.acolyptos.insurance.domain.response;

public class ApiError {

  private final String code;
  private final String field;
  private final String detail;

  public ApiError(String code, String field, String detail) {
    this.code = code;
    this.field = field;
    this.detail = detail;
  }

  public String getCode() {
    return code;
  }

  public String getField() {
    return field;
  }

  public String getDetail() {
    return detail;
  }

  @Override
  public String toString() {
    return "ApiError {\n\tcode: "
        + code
        + ",\n\tfield: "
        + field
        + ",\n\tdetail: "
        + detail
        + "\n}";
  }
}
