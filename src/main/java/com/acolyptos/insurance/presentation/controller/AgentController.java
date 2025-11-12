package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.AgentService;
import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequest;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

  private final AgentService agentService;

  public AgentController(final AgentService agentService) {
    this.agentService = agentService;
  }

  @PostMapping("/createAgent")
  public SuccessResponse<Agent> createAndSaveAgent(
      @RequestBody @Valid final AgentRegisterRequest agentRegisterRequest) {
    final Agent agent = agentService.createAgent(agentRegisterRequest);

    return new SuccessResponse<Agent>(
        HttpStatus.CREATED.value(), HttpStatus.CREATED, "Agent created successfully.", agent);
  }
}
