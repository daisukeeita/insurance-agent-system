package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.AgentService;
import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentLoginRequestDto;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

  private AgentService agentService;

  public AuthenticationController(AgentService agentService) {
    this.agentService = agentService;
  }

  @PostMapping("/loginUser")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public SuccessResponse<Agent> authenticateAgent(
      @RequestBody @Valid AgentLoginRequestDto agentLoginRequest) {
    Agent agent = agentService.loginAgent(agentLoginRequest);

    return new SuccessResponse<Agent>(
        HttpStatus.ACCEPTED.value(), HttpStatus.ACCEPTED, "Agent logged in successfuly.", agent);
  }
}
