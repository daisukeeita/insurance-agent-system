package com.acolyptos.insurance.domain.agent;

import com.acolyptos.insurance.domain.role.Role;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AgentDetailImplementation implements UserDetails {

  private final Agent agent;
  private final Collection<? extends GrantedAuthority> authorities;

  public AgentDetailImplementation(Agent agent) {
    this.agent = agent;
    this.authorities = mapRolesToAuthorities(agent.getAgentRoles());
  }

  private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()))
        .collect(Collectors.toUnmodifiableSet());
  }

  public Agent getAgent() {
    return agent;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return agent.getHashedPassword();
  }

  @Override
  public String getUsername() {
    return agent.getUsername();
  }
}
