package com.acolyptos.insurance.domain.insurer;

import jakarta.validation.constraints.NotBlank;

/** Entity that represents the request body for insurer registration. */
public class InsurerRegisterRequest {

  @NotBlank(message = "Insurer Name is required for registration.")
  private String insurerName;

  @NotBlank(message = "Insurer Address is required for registration.")
  private String insurerAddress;

  /** Protecting the default constructor of the class. */
  protected InsurerRegisterRequest() {}

  /**
   * Constructor that will be used when initializing the class.
   *
   * @param insurerName represents passed name from the client that will be used to register/save.
   * @param insurerAddress represents passed address from the client that will be used to
   *     register/save.
   */
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
