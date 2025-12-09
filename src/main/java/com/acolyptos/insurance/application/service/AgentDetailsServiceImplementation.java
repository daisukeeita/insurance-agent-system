package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentDetailImplementation;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AgentDetailsServiceImplementation implements UserDetailsService {

  private final AgentRepositoryInterface agentRepositoryInterface;

  public AgentDetailsServiceImplementation(AgentRepositoryInterface agentRepositoryInterface) {
    this.agentRepositoryInterface = agentRepositoryInterface;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Agent agent =
        agentRepositoryInterface
            .findAgentByUsername(username)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent not found with the username: " + username));

    return new AgentDetailImplementation(agent);
  }
}
