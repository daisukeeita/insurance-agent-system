package com.acolyptos.insurance.domain.insurer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Insurer Entity that represents the Insurance Company Details and its relationship to their Agent.
 */
@Entity
@Table(name = "insurers")
public class Insurer {

  @Id
  @Column(name = "insurer_id", nullable = false)
  @NotNull(message = "Insurer's ID is required.")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID insurerId;

  @NotBlank(message = "Name of the Insurer should not be blank.")
  @Column(name = "insurer_name", nullable = false, length = 100, unique = true)
  private String insurerName;

  @NotBlank(message = "Address of the Insurer should not be blank.")
  @Column(name = "insurer_address", nullable = false, length = 255)
  private String insurerAddress;

  /** Protecting the default Class Constructor of the Insurer. */
  protected Insurer() {}

  /** Class constructor of the Insurer Entity. */
  public Insurer(final String insurerName, final String insurerAddress) {
    this.insurerName = insurerName;
    this.insurerAddress = insurerAddress;
  }

  public void setInsurerId(final UUID insurerId) {
    this.insurerId = insurerId;
  }

  public void setInsurerName(final String insurerName) {
    this.insurerName = insurerName;
  }

  public void setInsurerAddress(final String insurerAddress) {
    this.insurerAddress = insurerAddress;
  }

  public UUID getInsurerId() {
    return insurerId;
  }

  public String getInsurerName() {
    return insurerName;
  }

  public String getInsurerAddress() {
    return insurerAddress;
  }

  @Override
  public String toString() {
    return "Insurer {\n\tinsurerId: "
        + insurerId
        + ",\n\tinsurerName: "
        + insurerName
        + ",\n\tinsurerAddress: "
        + insurerAddress
        + "\n}";
  }
}
