package com.acolyptos.insurance.domain.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Register Request DTO for Agent entity.
 *
 * <p>This DTO is typically used to carry the necessary information from a client request to
 * register or update an Agent.
 */
public class AgentRegisterRequestDto {

  @NotBlank(message = "Agent's username is required for registration.")
  private String username;

  @NotBlank(message = "Agent's password is required for registration.")
  @Size(min = 5, message = "Password should have minimum of 5 characters.")
  private String password;

  @NotBlank(message = "Agent's Insurer Company is required for registration.")
  private String insurer;

  @NotBlank(message = "Agent's first name is required for registration.")
  private String firstName;

  private String middleInitial;

  @NotBlank(message = "Agent's last name is required for registration.")
  private String lastName;

  @NotBlank(message = "Agent's License Number is required for registration.")
  private String licenseNumber;

  @NotBlank(message = "Agent's hiring date is required for registration.")
  private String dateHired;

  /** Constructs a new {@code AgentRegisterRequestDto}. */
  public AgentRegisterRequestDto() {}

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setInsurer(final String insurer) {
    this.insurer = insurer;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleInitial(final String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setLicenseNumber(final String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public void setDateHired(final String dateHired) {
    this.dateHired = dateHired;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getInsurer() {
    return insurer;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return firstName + " " + middleInitial + " " + lastName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public String getDateHired() {
    return dateHired;
  }

  @Override
  public String toString() {
    return "AgentRegisterRequest {\n\tusername: "
        + username
        + ",\n\tinsurer: "
        + insurer
        + ",\n\tlicenseNumber: "
        + licenseNumber
        + ",\n\tfullName: "
        + getFullName()
        + "\n}";
  }
}
