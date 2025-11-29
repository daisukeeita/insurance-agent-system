package com.acolyptos.insurance.domain.insurer;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO class for Insurer entity.
 *
 * <p>This DTO is typically used to carry the necessary information from a client request to
 * register or update an insurer data.
 */
public class InsurerRequestDto {

  @NotBlank(message = "Insurer Name must be included to the Insurer Request DTO.")
  private String insurerName;

  @NotBlank(message = "Insurer Address must be included to the Insurer Request DTO.")
  private String insurerAddress;

  /** Constructs a new {@code InsurerRequestDto}. */
  public InsurerRequestDto() {}

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
}
