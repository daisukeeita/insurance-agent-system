package com.acolyptos.insurance.domain.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Entity that represents the request body for agent registration. */
public class AgentRegisterRequest {

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

  /** Protecting the default constructor of the class. */
  protected AgentRegisterRequest() {}

  /**
   * Class constructor that will be used for initialization.
   *
   * @param username agent's username that will be used as credentials.
   * @param password agent's password that will be encoded and used as credentials.
   * @param insurer details of the insurer the agent is representing.
   * @param firstName first name of the agent.
   * @param middleInitial middle initial of the agent, if applicable.
   * @param lastName last name of the agent.
   * @param licenseNumber the license number of the agent, proof of its credibility.
   */
  public AgentRegisterRequest(
      final String username,
      final String password,
      final String insurer,
      final String firstName,
      final String middleInitial,
      final String lastName,
      final String licenseNumber,
      final String dateHired) {
    this.username = username;
    this.password = password;
    this.insurer = insurer;
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.licenseNumber = licenseNumber;
    this.dateHired = dateHired;
  }

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
