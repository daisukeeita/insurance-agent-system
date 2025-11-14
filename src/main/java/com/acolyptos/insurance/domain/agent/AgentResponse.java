package com.acolyptos.insurance.domain.agent;

import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.UUID;

/**
 * Class that will be used to filter the details of the Agent entity before sending the Success
 * Response.
 */
public class AgentResponse {

  private UUID agentId;

  private String username;

  private String fullName;

  private String licenseNumber;

  private Insurer insurer;

  /** The default constructor for this class. */
  public AgentResponse() {}

  public void setAgentId(UUID agentId) {
    this.agentId = agentId;
  }

  public void setInsurer(Insurer insurer) {
    this.insurer = insurer;
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

  public UUID getAgentId() {
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

  public Insurer getInsurer() {
    return insurer;
  }
}
