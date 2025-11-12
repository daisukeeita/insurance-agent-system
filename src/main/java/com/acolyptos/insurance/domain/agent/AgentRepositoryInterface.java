package com.acolyptos.insurance.domain.agent;

import java.util.List;

public interface AgentRepositoryInterface {

  Agent registerAgent(Agent agent);

  Agent findAgentByUsername(String username);

  Agent findAgentByLicenseNumber(String licenseNumber);

  List<Agent> findAllAgents();

  // void disableAgentByUsername(String username);
  //
  // void disableAgentByLicenseNumber(String licenseNumber);
}
