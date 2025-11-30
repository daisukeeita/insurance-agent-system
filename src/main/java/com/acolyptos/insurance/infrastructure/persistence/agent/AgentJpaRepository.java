package com.acolyptos.insurance.infrastructure.persistence.agent;

import com.acolyptos.insurance.domain.agent.Agent;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link Agent} entity, providing standard CRUD and custom
 * lookup methods.
 */
public interface AgentJpaRepository extends JpaRepository<Agent, UUID> {

  /**
   * Finds an {@link Agent} entity by its username.
   *
   * @param username The username of the agent to search for.
   * @return An {@link Optional} containing the {@link Agent} if found, otherwise empty.
   */
  Optional<Agent> findByUsername(String username);

  /**
   * Finds an {@link Agent} entity by its license number.
   *
   * @param licenseNumber The license number of the agent to search for.
   * @return An {@link Optional} containing the {@link Agent} if found, otherwise empty.
   */
  Optional<Agent> findByLicenseNumber(String licenseNumber);

  /**
   * Checks if an {@link Agent} entity exists with the given username.
   *
   * @param username The username of the agent to check for existence.
   * @return 'true' if an agent with given username exists, otherwise 'false'.
   */
  boolean existsByUsername(String username);

  /**
   * Checks if an {@link Agent} entity exists with the given license number.
   *
   * @param licenseNumber The license number of the agent to check for existence.
   * @return 'true' if an agent with the given license number exists, otherwise 'false'.
   */
  boolean existsByLicenseNumber(String licenseNumber);
}
