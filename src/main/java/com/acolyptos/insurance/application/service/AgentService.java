package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequest;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

  private final AgentRepositoryInterface agentRepositoryInterface;
  private final InsurerService insurerService;

  public AgentService(
      AgentRepositoryInterface agentRepositoryInterface, InsurerService insurerService) {
    this.agentRepositoryInterface = agentRepositoryInterface;
    this.insurerService = insurerService;
  }

  public Agent createAgent(AgentRegisterRequest agentRegisterRequest) {
    Agent checkIfExists =
        agentRepositoryInterface.findAgentByUsername(agentRegisterRequest.getUsername());
    if (checkIfExists != null) {
      throw new EntityAlreadyExistsException(
          "This agent is already exists with username" + " '" + checkIfExists.getUsername() + "'.");
    }

    Insurer insurer = insurerService.getInsurerByName(agentRegisterRequest.getInsurer());
    if (insurer == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Insurer with the name " + agentRegisterRequest.getInsurer());
    }

    String hashedPassword = hashPassword(agentRegisterRequest.getPassword());

    Agent agent = new Agent(agentRegisterRequest.getUsername());
    agent.setHashedPassword(hashedPassword);
    agent.setInsurer(insurer);
    agent.setFirstName(agentRegisterRequest.getFirstName());
    agent.setMiddleInitial(agentRegisterRequest.getMiddleInitial());
    agent.setLastName(agentRegisterRequest.getLastName());
    agent.setLicenseNumber(agentRegisterRequest.getLicenseNumber());

    return agentRepositoryInterface.registerAgent(agent);
  }

  public Agent getAgentByUsername(String username) {

    if (username.trim().isEmpty() || username == null) {
      throw new InvalidRequestBodyException("Agent's username is required.");
    }

    Agent agent = agentRepositoryInterface.findAgentByUsername(username);

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Agent with username: " + "'" + username + "'.");
    }

    return agent;
  }

  public Agent getAgentByLicenseNumber(String licenseNumber) {

    if (licenseNumber.trim().isEmpty() || licenseNumber == null) {
      throw new InvalidRequestBodyException("Agent's license number is required.");
    }

    Agent agent = agentRepositoryInterface.findAgentByLicenseNumber(licenseNumber);

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Agent with license number: " + "'" + licenseNumber + "'.");
    }

    return agent;
  }

  public List<Agent> getAllAgents() {
    return agentRepositoryInterface.findAllAgents();
  }

  private String hashPassword(String password) {
    return UUID.randomUUID().toString();
  }
}
