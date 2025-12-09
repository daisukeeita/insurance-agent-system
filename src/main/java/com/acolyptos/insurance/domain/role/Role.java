package com.acolyptos.insurance.domain.role;

import com.acolyptos.insurance.domain.agent.Agent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.util.Set;

/**
 * Represents an Role entity stored in the "roles" database table. This entity holds the details
 * about the authorized role of the Agent.
 */
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long roleId;

  @NotNull
  @Column(name = "role_name", nullable = false, unique = true)
  private String roleName;

  @Null
  @Column(name = "role_description", nullable = true)
  private String roleDescription;

  @ManyToMany(mappedBy = "roles")
  private Set<Agent> agents;

  /** Constructs a new {@code Role}. */
  public Role() {}

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  public String getRoleName() {
    return roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  @Override
  public String toString() {
    return "Role {\n\tId: "
        + roleId
        + ",\n\tName: "
        + roleName
        + ",\n\tDescription: "
        + roleDescription
        + "\n}";
  }
}
