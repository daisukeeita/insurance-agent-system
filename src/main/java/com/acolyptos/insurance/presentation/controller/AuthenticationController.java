package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentDetailImplementation;
import com.acolyptos.insurance.domain.agent.AgentLoginRequestDto;
import com.acolyptos.insurance.domain.agent.AgentResponseDto;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import com.acolyptos.insurance.infrastructure.jwt.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public AuthenticationController(
      final AuthenticationManager authenticationManager, final JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/login")
  public ResponseEntity<SuccessResponse<AgentResponseDto>> loginAgent(
      @RequestBody final AgentLoginRequestDto agentLoginRequestDto) {

    final Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                agentLoginRequestDto.getUsername(), agentLoginRequestDto.getPassword()));

    if (authentication.isAuthenticated()) {
      final AgentDetailImplementation agentDetails =
          (AgentDetailImplementation) authentication.getPrincipal();
      final Agent agent = agentDetails.getAgent();

      final String token = jwtUtil.generateToken(agentLoginRequestDto.getUsername());
      final AgentResponseDto agentResponseDto = new AgentResponseDto();
      agentResponseDto.setAgentId(agent.getAgentId().toString());
      agentResponseDto.setFullName(agent.getFullName());
      agentResponseDto.setUsername(agent.getUsername());
      agentResponseDto.setInsurerName(agent.getInsurer().getInsurerName());
      agentResponseDto.setLicenseNumber(agent.getLicenseNumber());

      final SuccessResponse<AgentResponseDto> successResponse =
          new SuccessResponse<AgentResponseDto>(
              HttpStatus.OK.value(),
              HttpStatus.OK,
              "Agent logged in successfully.",
              agentResponseDto);

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
      httpHeaders.add("Access-Control-Expose-Headers", HttpHeaders.AUTHORIZATION);

      return new ResponseEntity<SuccessResponse<AgentResponseDto>>(
          successResponse, httpHeaders, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
