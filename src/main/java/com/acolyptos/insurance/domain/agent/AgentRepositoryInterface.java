package com.acolyptos.insurance.domain.agent;

import java.util.List;
import java.util.UUID;

/** The blueprint that will be used for Agent Repository. */
public interface AgentRepositoryInterface {

  /**
   * Method for saving the details of the agent to the database.
   *
   * @param agent entity that will be saved to the databse.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent registerAgent(Agent agent);

  /**
   * Method for looking to a specific agent from the database.
   *
   * @param username string that will be used to look for that agent.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent findAgentByUsername(String username);

  /**
   * Method for looking to a specific agent from the database.
   *
   * @param licenseNumber string that will be used to look for that agent.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent findAgentByLicenseNumber(String licenseNumber);

  /**
   * Method for looking to a specific agent from the database.
   *
   * @param agentId string that will be used to look for a specific agent.
   * @return {@link Agent} details of the agent upon successful database process.
   */
  Agent findAgentById(UUID agentId);

  /**
   * Method for returning the lists of all registered agents.
   *
   * @return {@link Agent} lists details of the agent upon successful process.
   */
  List<Agent> findAllAgents();

  // void disableAgentByUsername(String username);
  //
  // void disableAgentByLicenseNumber(String licenseNumber);
}
