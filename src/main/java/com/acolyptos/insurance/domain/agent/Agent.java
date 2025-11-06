// package com.acolyptos.insurance.domain.agent;
//
// import com.acolyptos.insurance.domain.insurer.Insurer;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.UUID;
//
// /**
//  * Agent Entity that represents the Insurance Agent Details and its relationship to their
// Insurance
//  * Company/Agency.
//  */
// @Entity
// @Table(name = "agents")
// public class Agent {
//
//   @Id
//   @NotNull(message = "Agent's ID is required.")
//   @Column(name = "agent_id", nullable = false)
//   @GeneratedValue(strategy = GenerationType.UUID)
//   private UUID agentId;
//
//   @ManyToOne
//   @NotNull(message = "Insurer's information is required.")
//   @JoinColumn(name = "insurer_id", referencedColumnName = "insurer_id", nullable = false)
//   private Insurer insurer;
//
//   @NotBlank(message = "Agent's `username` was not provided.")
//   @Column(name = "username", nullable = false, length = 50, unique = true)
//   private String username;
//
//   @NotBlank(message = "Agent's `hashed password` was not provided.")
//   @Column(name = "hashed_password", nullable = false, length = 255)
//   private String hashedPassword;
//
//   @NotBlank(message = "Agent's `first name` was not provided.")
//   @Column(name = "first_name", nullable = false, length = 100)
//   private String firstName;
//
//   @Column(name = "middle_initial", nullable = true, length = 10)
//   private String middleInitial;
//
//   @NotBlank(message = "Agent's `last name` was not provided.")
//   @Column(name = "last_name", nullable = false, length = 100)
//   private String lastName;
//
//   @NotBlank(message = "Agent's `license number` was not provided.")
//   @Column(name = "license_number", nullable = false, length = 50, unique = true)
//   private String licenseNumber;
//
//   @NotNull(message = "Agent's `date hired` was not provided.")
//   @Column(name = "date_hired", nullable = false)
//   private LocalDate dateHired;
//
//   @NotNull(message = "Agent's creation date is required.")
//   @Column(name = "created_at", nullable = false, updatable = false, insertable = true)
//   private LocalDateTime createdAt;
//
//   @NotNull(message = "Agent's modification date is required.")
//   @Column(name = "updated_at", nullable = false, updatable = true, insertable = true)
//   private LocalDateTime updatedAt;
//
//   /** Protecting the default Class Constructor of the Agent. */
//   protected Agent() {}
//
//   /** Class constructor WITHOUT Middle Initial. */
//   public Agent(
//       final String username,
//       final String hashedPassword,
//       final Insurer insurer,
//       final String firstName,
//       final String lastName,
//       final String licenseNumber,
//       final LocalDate dateHired) {
//     this.username = username;
//     this.hashedPassword = hashedPassword;
//     this.insurer = insurer;
//     this.firstName = firstName;
//     this.lastName = lastName;
//     this.licenseNumber = licenseNumber;
//     this.dateHired = dateHired;
//     this.createdAt = LocalDateTime.now();
//     this.updatedAt = LocalDateTime.now();
//   }
//
//   /** Class constructor WITH Middle Initial. */
//   public Agent(
//       final String username,
//       final String hashedPassword,
//       final Insurer insurer,
//       final String firstName,
//       final String middleInitial,
//       final String lastName,
//       final String licenseNumber,
//       final LocalDate dateHired) {
//     this.username = username;
//     this.hashedPassword = hashedPassword;
//     this.insurer = insurer;
//     this.firstName = firstName;
//     this.middleInitial = middleInitial;
//     this.lastName = lastName;
//     this.licenseNumber = licenseNumber;
//     this.dateHired = dateHired;
//     this.createdAt = LocalDateTime.now();
//     this.updatedAt = LocalDateTime.now();
//   }
//
//   public void setAgentId(final UUID agentId) {
//     this.agentId = agentId;
//   }
//
//   public void setInsurer(final Insurer insurer) {
//     this.insurer = insurer;
//   }
//
//   public void setUsername(final String username) {
//     this.username = username;
//   }
//
//   public void setHashedPassword(final String hashedPassword) {
//     this.hashedPassword = hashedPassword;
//   }
//
//   public void setFirstName(final String firstName) {
//     this.firstName = firstName;
//   }
//
//   public void setMiddleInitial(final String middleInitial) {
//     this.middleInitial = middleInitial;
//   }
//
//   public void setLastName(final String lastName) {
//     this.lastName = lastName;
//   }
//
//   public void setLicenseNumber(final String licenseNumber) {
//     this.licenseNumber = licenseNumber;
//   }
//
//   public void setDateHired(final LocalDate dateHired) {
//     this.dateHired = dateHired;
//   }
//
//   public void setUpdatedAt(final LocalDateTime updatedAt) {
//     this.updatedAt = updatedAt;
//   }
//
//   public UUID getAgentId() {
//     return agentId;
//   }
//
//   public Insurer getInsurer() {
//     return insurer;
//   }
//
//   public String getUsername() {
//     return username;
//   }
//
//   public String getHashedPassword() {
//     return hashedPassword;
//   }
//
//   public String getFullName() {
//     return firstName + " " + middleInitial + " " + lastName;
//   }
//
//   public String getLicenseNumber() {
//     return licenseNumber;
//   }
//
//   public LocalDate getDateHired() {
//     return dateHired;
//   }
//
//   @Override
//   public String toString() {
//     return "Agent {\n\tagentId: "
//         + agentId
//         + ",\n\tinsurer: "
//         + insurer
//         + ",\n\tusername: "
//         + username
//         + ",\n\thashedPassword: "
//         + hashedPassword
//         + ",\n\tlicenseNumber: "
//         + licenseNumber
//         + ",\n\tdateHired: "
//         + dateHired
//         + ",\n\tcreatedAt: "
//         + createdAt
//         + ",\n\tupdatedAt: "
//         + updatedAt
//         + ",\n\tgetFullName(): "
//         + getFullName()
//         + "\n}";
//   }
// }
