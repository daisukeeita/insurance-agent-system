package com.acolyptos.insurance.domain.agent;

import java.util.Set;

/**
 * Class that will be used to filter the details of the Agent entity before sending the Success
 * Response.
 */
public class AgentResponseDto {

  private String agentId;

  private String username;

  private String fullName;

  private String licenseNumber;

  private String insurerName;

  private Set<String> agentRoles;

  /** The default constructor for this class. */
  public AgentResponseDto() {}

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public void setInsurerName(String insurerName) {
    this.insurerName = insurerName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public void setRoles(Set<String> agentRoles) {
    this.agentRoles = agentRoles;
  }

  public String getAgentId() {
    return agentId;
  }

  public String getUsername() {
    return username;
  }

  public String getFullName() {
    return fullName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public String getInsurerName() {
    return insurerName;
  }

  public Set<String> getAgentRoles() {
    return agentRoles;
  }
}
