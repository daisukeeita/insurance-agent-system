package com.acolyptos.insurance.domain.agent;

import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents an Agent entity stored in the "agents" database table. This entity holds the core
 * details about the Insurance's Agent.
 */
@Entity
@Table(name = "agents")
public class Agent {

  @Id
  @NotNull(message = "Agent's ID is required.")
  @Column(name = "agent_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID agentId;

  @ManyToOne
  @NotNull(message = "Insurer's information is required.")
  @JoinColumn(name = "insurer_id", referencedColumnName = "insurer_id", nullable = false)
  private Insurer insurer;

  @NotBlank(message = "Agent's `username` was not provided.")
  @Column(name = "username", nullable = false, length = 50, unique = true)
  private String username;

  @NotBlank(message = "Agent's `hashed password` was not provided.")
  @Column(name = "hashed_password", nullable = false, length = 255)
  private String hashedPassword;

  @NotBlank(message = "Agent's full name my be included before saving to the database.")
  @Column(name = "full_name", nullable = false, length = 150)
  private String fullName;

  @NotBlank(message = "Agent's `license number` was not provided.")
  @Column(name = "license_number", nullable = false, length = 50, unique = true)
  private String licenseNumber;

  @NotNull(message = "Agent's hiring date was not provided.")
  @Column(name = "date_hired", nullable = false)
  private LocalDate dateHired;

  @NotNull(message = "Agent's creation date was not provided.")
  @Column(name = "created_at", nullable = false, updatable = false, insertable = true)
  private LocalDate createdAt;

  @NotNull(message = "Agent's modification date was not provided.")
  @Column(name = "updated_at", nullable = false, updatable = true, insertable = true)
  private LocalDate updatedAt;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "agent_roles",
      joinColumns = @JoinColumn(name = "agent_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> agentRoles = new HashSet<Role>();

  @Column(name = "enabled", nullable = false)
  private boolean enabled = true;

  @Column(name = "account_non_expired", nullable = false)
  private boolean accountNonExpired = true;

  @Column(name = "credentials_non_expired", nullable = false)
  private boolean credentialsNonExpired = true;

  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked = true;

  /**
   * Default constructor required by the JPA/Hibernate. Protected to prevent direct instantiation.
   */
  protected Agent() {}

  /**
   * Constructs a new Agent entity with required initial data.
   *
   * @param username The username credential of the agent, it must be unique.
   */
  public Agent(
      Insurer insurer,
      String username,
      String hashedPassword,
      String fullName,
      String licenseNumber,
      LocalDate dateHired) {
    this.insurer = insurer;
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.fullName = fullName;
    this.licenseNumber = licenseNumber;
    this.dateHired = dateHired;
    this.createdAt = LocalDate.now();
    this.updatedAt = LocalDate.now();
  }

  public void setAgentId(final UUID agentId) {
    this.agentId = agentId;
  }

  public void setInsurer(final Insurer insurer) {
    this.insurer = insurer;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setHashedPassword(final String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }

  public void setLicenseNumber(final String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  public void setDateHired(final LocalDate dateHired) {
    this.dateHired = dateHired;
  }

  public void setUpdatedAt(final LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setAgentEnable(Boolean enable) {
    this.enabled = enable;
  }

  public void setAgentAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public void setAgentCredentialsNonExpired(Boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public void setAgentAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public UUID getAgentId() {
    return agentId;
  }

  public Insurer getInsurer() {
    return insurer;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public String getFullName() {
    return fullName;
  }

  public String getLicenseNumber() {
    return licenseNumber;
  }

  public LocalDate getDateHired() {
    return dateHired;
  }

  public Set<Role> getAgentRoles() {
    return agentRoles;
  }

  public boolean getAgentEnabled() {
    return enabled;
  }

  public boolean getAgentAccountNonExpired() {
    return accountNonExpired;
  }

  public boolean getAgentCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public boolean getAgentAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public String toString() {
    return "Agent {\n\tagentId: "
        + agentId
        + ",\n\tinsurer: "
        + insurer
        + ",\n\tusername: "
        + username
        + ",\n\tlicenseNumber: "
        + licenseNumber
        + ",\n\tFullName(): "
        + getFullName()
        + "\n}";
  }
}
