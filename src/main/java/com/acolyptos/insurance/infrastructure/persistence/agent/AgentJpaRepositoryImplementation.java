// package com.acolyptos.insurance.infrastructure.persistence.agent;
//
// import com.acolyptos.insurance.domain.agent.Agent;
// import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
// import java.util.List;
// import java.util.Optional;
// import org.springframework.stereotype.Repository;
//
// @Repository
// public class AgentJpaRepositoryImplementation implements AgentRepositoryInterface {
//
//   private final AgentJpaRepository agentJpaRepository;
//
//   public AgentJpaRepositoryImplementation(AgentJpaRepository agentJpaRepository) {
//     this.agentJpaRepository = agentJpaRepository;
//   }
//
//   @Override
//   public Optional<Agent> findAgentByLicenseNumber(String licenseNumber) {
//     return agentJpaRepository.findByLicenseNumber(licenseNumber);
//   }
//
//   @Override
//   public Optional<Agent> findAgentByUsername(String username) {
//     return agentJpaRepository.findByUsername(username);
//   }
//
//   @Override
//   public List<Agent> findAllAgents() {
//     return agentJpaRepository.findAll();
//   }
//
//   @Override
//   public Agent registerAgent(Agent agent) {
//     return agentJpaRepository.save(agent);
//   }
//
//   @Override
//   public void disableAgentByLicenseNumber(String licenseNumber) {
//     // TODO Auto-generated method stub
//
//   }
//
//   @Override
//   public void disableAgentByUsername(String username) {
//     // TODO Auto-generated method stub
//   }
// }
