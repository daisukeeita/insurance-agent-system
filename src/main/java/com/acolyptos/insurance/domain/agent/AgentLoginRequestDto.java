package com.acolyptos.insurance.domain.agent;

import jakarta.validation.constraints.NotBlank;

/**
 * Login Request DTO class for Agent entity.
 *
 * <p>This DTO is typically used to carry the necessary information from a client request to login
 * an agent.
 */
public class AgentLoginRequestDto {

  @NotBlank(message = "Agent's username must be included to the Agent Login Request DTO.")
  private String username;

  @NotBlank(message = "Agent's password must be included to the Agent Login Request DTO.")
  private String password;

  /** Constructs a new {@code AgentLoginRequestDto}. */
  public AgentLoginRequestDto() {}

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
