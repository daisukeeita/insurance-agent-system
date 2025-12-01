package com.acolyptos.insurance.domain.agent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Repository interface for managing {@link Agent} entities. */
public interface AgentRepositoryInterface {

  /**
   * Persists the given {@link Agent} entity to the database.
   *
   * @param agent The entity to be saved.
   * @return The saved {@link Agent} entity.
   */
  Agent saveAgent(Agent agent);

  /**
   * Finds an single {@link Agent} entity by its name.
   *
   * @param username The unique username credential of the agent to find.
   * @return An {@link Optional} containing the {@link Agent} if found, otherwise empty.
   */
  Optional<Agent> findAgentByUsername(String username);

  /**
   * Finds a single {@link Agent} entity by its license number.
   *
   * @param licenseNumber The licence number of the agent to find.
   * @return An {@link Optional} containing the {@link Agent} if found, otherwise empty.
   */
  Optional<Agent> findAgentByLicenseNumber(String licenseNumber);

  /**
   * Finds a single {@link Agent} entity by its unique ID.
   *
   * @param agentId The unique ID of the agent to find.
   * @return An {@link Optional} containing the {@link Agent if found, otherwise empty.}
   */
  Optional<Agent> findAgentById(UUID agentId);

  /**
   * Checks if an {@link Agent} entity with the given unique ID exists in the database.
   *
   * @param agentId The unique ID of the agent to check for existence.
   * @return 'true' if an agent exists with te given unique ID, otherwise 'false'.
   */
  boolean checkAgentIfExistsById(UUID agentId);

  /**
   * Checks if an {@link Agent} entity with the given username exists in the database.
   *
   * @param username The username of the agent to check for existence.
   * @return 'true' if an agent exists with the given username, otherwise 'false'.
   */
  boolean checkAgentIfExistsByUsername(String username);

  /**
   * Checks if an {@link Agent} entity with the given license number exists in the database.
   *
   * @param licenseNumber The license number of the agent to check for existence.
   * @return 'true' if an agent exists with the given license number, otherwise 'false'.
   */
  boolean checkAgentIfExistsByLicenseNumber(String licenseNumber);

  /**
   * Retrieves a paginated list of all {@link Agent} entities.
   *
   * @param pageable The pagination information of (number, size, sort).
   * @return A {@link Page} of {@link Agent} entities.
   */
  Page<Agent> getPaginatedAgents(Pageable pageable);

  /**
   * Retrieves all {@link Agent} entities from the database.
   *
   * @return A list of all {@link Agent} entities.
   */
  List<Agent> findAllAgents();
}
