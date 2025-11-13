package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.AgentService;
import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequest;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

  private final AgentService agentService;

  public AgentController(final AgentService agentService) {
    this.agentService = agentService;
  }

  @PostMapping("/createAgent")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<Agent> createAndSaveAgent(
      @RequestBody @Valid final AgentRegisterRequest agentRegisterRequest) {
    final Agent agent = agentService.createAgent(agentRegisterRequest);

    return new SuccessResponse<Agent>(
        HttpStatus.CREATED.value(), HttpStatus.CREATED, "Agent created successfully.", agent);
  }

  @GetMapping("/getAgentByUsername/{username}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<Agent> findAndGetAgentByUsername(
      @PathVariable("username") final String username) {
    Agent agent = agentService.getAgentByUsername(username);

    return new SuccessResponse<Agent>(
        HttpStatus.FOUND.value(), HttpStatus.FOUND, "Successfully found the Agent.", agent);
  }

  @GetMapping("/getAgentByLicenseNumber/{licenseNumber}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<Agent> findAndGetAgentByLicenseNumber(
      @PathVariable("licenseNumber") final String licenseNumber) {
    Agent agent = agentService.getAgentByLicenseNumber(licenseNumber);

    return new SuccessResponse<Agent>(
        HttpStatus.FOUND.value(), HttpStatus.FOUND, "Successfully found the Agent.", agent);
  }

  @GetMapping("/getAllAgents")
  public List<Agent> getAllAgents() {
    return agentService.getAllAgents();
  }
}
