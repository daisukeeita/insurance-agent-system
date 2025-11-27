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
 * Represents an Insurer (Insurance Company) entity stored in the "insurers" database table. This
 * entity holds the core details about the company and is parent in the one-to-many relationship
 * with {@link Agent} entities.
 */
@Entity
@Table(name = "insurers")
public class Insurer {

  /** The unique identifier (Primary Key) for the insurer. Generated automatically as a UUID. */
  @Id
  @Column(name = "insurer_id", nullable = false)
  @NotNull(message = "Insurer's ID is required.")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID insurerId;

  /** The official, unique name of the insurer. Mapped to a non-nullable, unique column. */
  @NotBlank(message = "Name of the Insurer should not be blank.")
  @Column(name = "insurer_name", nullable = false, length = 100, unique = true)
  private String insurerName;

  /** The primary business address of the insurer. Mapped to a non-nullable column. */
  @NotBlank(message = "Address of the Insurer should not be blank.")
  @Column(name = "insurer_address", nullable = false, length = 255)
  private String insurerAddress;

  /**
   * Default constructor required by the JPA/Hibernate. Protected. to prevent direct instantiation.
   */
  protected Insurer() {}

  /**
   * Creates a new Insurer entity with required initial data.
   *
   * @param insurerName The name of the insurer, it must be unique.
   * @param insurerAddress The primary address of the insurer.
   */
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

  /**
   * The string representation of the Insurer entity, useful for logging and debugging.
   *
   * @return A formatted string containing entity's fields.
   */
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
