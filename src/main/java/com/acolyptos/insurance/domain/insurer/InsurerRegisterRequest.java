package com.acolyptos.insurance.domain.insurer;

import jakarta.validation.constraints.NotBlank;

public class InsurerRegisterRequest {

  @NotBlank(message = "Insurer Name is required for registration.")
  private String insurerName;

  @NotBlank(message = "Insurer Address is required for registration.")
  private String insurerAddress;

  protected InsurerRegisterRequest() {}

  public InsurerRegisterRequest(final String insurerName, final String insurerAddress) {
    this.insurerName = insurerName;
    this.insurerAddress = insurerAddress;
  }

  public void setInsurerName(final String insurerName) {
    this.insurerName = insurerName;
  }

  public void setInsurerAddress(final String insurerAddress) {
    this.insurerAddress = insurerAddress;
  }

  public String getInsurerName() {
    return insurerName;
  }

  public String getInsurerAddress() {
    return insurerAddress;
  }

  @Override
  public String toString() {
    return "InsurerRegisterRequest {\n\tinsurerName: "
        + insurerName
        + ",\n\tinsurerAddress: "
        + insurerAddress
        + "\n}";
  }
}
