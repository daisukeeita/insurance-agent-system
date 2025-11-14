package com.acolyptos.insurance.infrastructure.persistence.agent;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import java.util.List;
import org.springframework.stereotype.Repository;

/** Repository class that implements the interface of {@link AgentRepositoryInterface}. */
@Repository
public class AgentJpaRepositoryImplementation implements AgentRepositoryInterface {

  private final AgentJpaRepository agentJpaRepository;

  /** Constructor of the class with injected {@link AgentJpaRepository} to use its methods. */
  public AgentJpaRepositoryImplementation(AgentJpaRepository agentJpaRepository) {
    this.agentJpaRepository = agentJpaRepository;
  }

  @Override
  public Agent registerAgent(Agent agent) {
    return agentJpaRepository.save(agent);
  }

  @Override
  public Agent findAgentByLicenseNumber(String licenseNumber) {
    return agentJpaRepository.findByLicenseNumber(licenseNumber);
  }

  @Override
  public Agent findAgentByUsername(String username) {
    return agentJpaRepository.findByUsername(username);
  }

  @Override
  public List<Agent> findAllAgents() {
    return agentJpaRepository.findAll();
  }
}
