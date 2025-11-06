// package com.acolyptos.insurance.application.service;
//
// import com.acolyptos.insurance.domain.agent.Agent;
// import com.acolyptos.insurance.domain.agent.AgentRegisterRequest;
// import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
// import com.acolyptos.insurance.domain.insurer.Insurer;
// import java.util.Optional;
// import java.util.UUID;
// import org.springframework.stereotype.Service;
//
// @Service
// public class AgentService {
//
//   private final AgentRepositoryInterface agentRepositoryInterface;
//
//   public AgentService(AgentRepositoryInterface agentRepositoryInterface) {
//     this.agentRepositoryInterface = agentRepositoryInterface;
//   }
//
//   public Agent createAgent(AgentRegisterRequest agentRegisterRequest) {
//     Insurer insurer = new Insurer("John Doe's Insurance", "Somewhere near Palawan.");
//     insurer.setInsurerId(UUID.randomUUID());
//     Agent agent =
//         new Agent(
//             agentRegisterRequest.getUsername(),
//             agentRegisterRequest.getPassword(),
//             insurer,
//             agentRegisterRequest.getFirstName(),
//             agentRegisterRequest.getMiddleInitial(),
//             agentRegisterRequest.getLastName(),
//             agentRegisterRequest.getLicenseNumber(),
//             agentRegisterRequest.getDateHired());
//     agent.setAgentId(UUID.randomUUID());
//
//     return agentRepositoryInterface.registerAgent(agent);
//   }
//
//   public Optional<Agent> getAgentByUsername(String username) {
//     return agentRepositoryInterface.findAgentByUsername(username);
//   }
// }
