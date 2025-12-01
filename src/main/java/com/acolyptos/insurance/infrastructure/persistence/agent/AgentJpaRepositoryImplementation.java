package com.acolyptos.insurance.infrastructure.persistence.agent;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * The implementation of the {@link InsurerRepositoryInterface} that uses {@link
 * InsurerJpaRepository} to interact with the database.
 */
@Repository
public class AgentJpaRepositoryImplementation implements AgentRepositoryInterface {

  private final AgentJpaRepository agentJpaRepository;

  /**
   * Constructs the repository implementation and injects the necessary JPA component.
   *
   * @param agentJpaRepository The Spring Data JPA repository used for direct database access.
   */
  public AgentJpaRepositoryImplementation(AgentJpaRepository agentJpaRepository) {
    this.agentJpaRepository = agentJpaRepository;
  }

  @Override
  public Agent saveAgent(Agent agent) {
    return agentJpaRepository.save(agent);
  }

  @Override
  public Optional<Agent> findAgentByUsername(String username) {
    return agentJpaRepository.findByUsername(username);
  }

  @Override
  public Optional<Agent> findAgentByLicenseNumber(String licenseNumber) {
    return agentJpaRepository.findByLicenseNumber(licenseNumber);
  }

  @Override
  public Optional<Agent> findAgentById(UUID agentId) {
    return agentJpaRepository.findById(agentId);
  }

  @Override
  public boolean checkAgentIfExistsById(UUID agentId) {
    return agentJpaRepository.existsById(agentId);
  }

  @Override
  public boolean checkAgentIfExistsByUsername(String username) {
    return agentJpaRepository.existsByUsername(username);
  }

  @Override
  public boolean checkAgentIfExistsByLicenseNumber(String licenseNumber) {
    return agentJpaRepository.existsByLicenseNumber(licenseNumber);
  }

  @Override
  public Page<Agent> getPaginatedAgents(Pageable pageable) {
    return agentJpaRepository.findAll(pageable);
  }

  @Override
  public List<Agent> findAllAgents() {
    return agentJpaRepository.findAll();
  }
}
