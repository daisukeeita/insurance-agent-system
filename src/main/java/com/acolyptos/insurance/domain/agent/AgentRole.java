package com.acolyptos.insurance.domain.agent;

import java.util.Optional;

/** The authorization level of an {@link Agent}. */
public enum AgentRole {
  ADMIN,
  AGENT,
  SEARCH;

  /**
   * Converts a case-insensitive status string into a {@code AgentRole} Enum.
   *
   * @param role The string representation of role.
   * @return An {@link Optional} containing the matching {@code AgentRole}, otherwise empty.
   */
  public static Optional<AgentRole> fromString(String role) {
    if (role == null) {
      return Optional.empty();
    }

    for (AgentRole agentRole : AgentRole.values()) {
      if (agentRole.name().equalsIgnoreCase(role.trim())) {
        return Optional.of(agentRole);
      }
    }

    return Optional.empty();
  }
}
