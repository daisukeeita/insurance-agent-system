package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentLoginRequest;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequest;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.agent.AgentResponse;
import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/** Service class for agent entity. */
@Service
public class AgentService {

  private final AgentRepositoryInterface agentRepositoryInterface;
  private final InsurerService insurerService;

  /**
   * Constructor for Agent Service class.
   *
   * @param agentRepositoryInterface injected interface to have an access to database.
   * @param insurerService injected service class for insurer to have an access to insurer entity.
   */
  public AgentService(
      AgentRepositoryInterface agentRepositoryInterface, InsurerService insurerService) {
    this.agentRepositoryInterface = agentRepositoryInterface;
    this.insurerService = insurerService;
  }

  /**
   * Method to process and validate the {@link AgentRegisterRequest} and saves it to the database.
   * It'll return a filtered detail of the {@link Agent} upon successful process.
   *
   * @param agentRegisterRequest the request body of the client that will be processed and
   *     validated.
   * @return a filtered {@link AgentResponse} if the process is successful.
   * @throws EntityAlreadyExistsException if the agent already exists after checking from the
   *     database.
   * @throws EntityDoesNotExistException if the insurer does not exists after checking to the
   *     database.
   */
  public AgentResponse createAgent(AgentRegisterRequest agentRegisterRequest) {
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

    Agent savedAgent = agentRepositoryInterface.registerAgent(agent);

    AgentResponse agentResponse = new AgentResponse();
    agentResponse.setAgentId(savedAgent.getAgentId());
    agentResponse.setUsername(savedAgent.getUsername());
    agentResponse.setInsurer(savedAgent.getInsurer());
    agentResponse.setFullName(savedAgent.getFullName());
    agentResponse.setLicenseNumber(savedAgent.getLicenseNumber());

    return agentResponse;
  }

  /** Method for authenticating the agent. */
  public Agent loginAgent(AgentLoginRequest agentLoginRequest) {
    Agent agent = agentRepositoryInterface.findAgentByUsername(agentLoginRequest.getUsername());

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Agent with username: " + "'" + agentLoginRequest.getUsername() + "'.");
    }

    return agentRepositoryInterface.findAgentByUsername(agentLoginRequest.getUsername());
  }

  /**
   * Method to look for an Agent using its username.
   *
   * @param username the string that will be used to look for the agent.
   * @return the filtered {@link AgentResponse} if the query is successful.
   * @throws InvalidRequestBodyException if the username is empty or null.
   * @throws EntityDoesNotExistException if there's no agent found from the database.
   */
  public AgentResponse getAgentByUsername(String username) {

    if (username.trim().isEmpty() || username == null) {
      throw new InvalidRequestBodyException("Agent's username is required.");
    }

    Agent agent = agentRepositoryInterface.findAgentByUsername(username);

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Agent with username: " + "'" + username + "'.");
    }

    AgentResponse agentResponse = new AgentResponse();
    agentResponse.setAgentId(agent.getAgentId());
    agentResponse.setUsername(agent.getUsername());
    agentResponse.setFullName(agent.getFullName());
    agentResponse.setLicenseNumber(agent.getLicenseNumber());
    agentResponse.setInsurer(agent.getInsurer());

    return agentResponse;
  }

  /**
   * Method to look for an Agent using its username.
   *
   * @param licenseNumber the string that will be used to look for the agent.
   * @return the filtered {@link AgentResponse} if the query is successful.
   * @throws InvalidRequestBodyException if the username is empty or null.
   * @throws EntityDoesNotExistException if there's no agent found from the database.
   */
  public AgentResponse getAgentByLicenseNumber(String licenseNumber) {

    if (licenseNumber.trim().isEmpty() || licenseNumber == null) {
      throw new InvalidRequestBodyException("Agent's license number is required.");
    }

    Agent agent = agentRepositoryInterface.findAgentByLicenseNumber(licenseNumber);

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Cannot find an Agent with license number: " + "'" + licenseNumber + "'.");
    }

    AgentResponse agentResponse = new AgentResponse();
    agentResponse.setAgentId(agent.getAgentId());
    agentResponse.setUsername(agent.getUsername());
    agentResponse.setFullName(agent.getFullName());
    agentResponse.setLicenseNumber(agent.getLicenseNumber());
    agentResponse.setInsurer(agent.getInsurer());

    return agentResponse;
  }

  public List<Agent> getAllAgents() {
    return agentRepositoryInterface.findAllAgents();
  }

  private String hashPassword(String password) {
    return UUID.randomUUID().toString();
  }
}
