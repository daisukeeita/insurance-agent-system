package com.acolyptos.insurance.domain.insurer;

import jakarta.validation.constraints.NotBlank;

/**
 * Response DTO class for Insurer entity.
 *
 * <p>This DTO is typically used to carry the necessary information that will be sent to the client.
 */
public class InsurerResponseDto {

  @NotBlank(message = "Inusurer ID must be included to the Insurer Response DTO.")
  private String insurerId;

  @NotBlank(message = "Insurer Name must be included to the Insurer Response DTO.")
  private String insurerName;

  @NotBlank(message = "Insurer Address must be included to the Insurer Response DTO.")
  private String insurerAddress;

  /** Constructs a new {@code InsurerResponseDto} */
  public InsurerResponseDto() {}

  public void setInsurerId(final String insurerId) {
    this.insurerId = insurerId;
  }

  public void setInsurerName(final String insurerName) {
    this.insurerName = insurerName;
  }

  public void setInsurerAddress(final String insurerAddress) {
    this.insurerAddress = insurerAddress;
  }

  public String getInsurerId() {
    return insurerId;
  }

  public String getInsurerName() {
    return insurerName;
  }

  public String getInsurerAddress() {
    return insurerAddress;
  }
}
