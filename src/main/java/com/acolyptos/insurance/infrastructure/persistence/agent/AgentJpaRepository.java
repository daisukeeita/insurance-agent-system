package com.acolyptos.insurance.infrastructure.persistence.agent;

import com.acolyptos.insurance.domain.agent.Agent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that extends the {@link JpaRepository} to access the methods for relational database.
 */
public interface AgentJpaRepository extends JpaRepository<Agent, UUID> {

  /**
   * Method for saving the details of the agent to the database.
   *
   * @param agent entity that will be saved to the databse.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent save(Agent agent);

  /**
   * Method for looking to a specific agent from the database.
   *
   * @param username string that will be used to look for that agent.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent findByUsername(String username);

  /**
   * Method for returning the lists of all registered agents.
   *
   * @return {@link Agent} lists details of the agent upon successful process.
   */
  Agent findByLicenseNumber(String licenseNumber);
}
