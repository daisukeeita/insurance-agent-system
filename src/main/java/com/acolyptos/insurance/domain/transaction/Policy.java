package com.acolyptos.insurance.domain.transaction;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
public class Policy {

  @Id
  @Column(
      name = "policy_id",
      nullable = false,
      length = 36,
      insertable = true,
      updatable = false,
      unique = true)
  @NotBlank(message = "Policy ID was not provided.")
  private String policyId;

  @OneToOne
  @JoinColumn(name = "coc_number", referencedColumnName = "coc_number")
  @NotNull(message = "Certificate of Coverage Entity was not provided under Policy Entity.")
  private CertificateOfCoverage certificateOfCoverage;

  @OneToOne
  @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
  @NotNull(message = "Agent Entity was not provided under Policy Entity.")
  private Agent agent;

  @Column(name = "customer_name", nullable = false, length = 100)
  @NotBlank(message = "Customer's name was not provided under Policy Entity.")
  private String customerName;

  @Column(name = "plate_number", nullable = false, length = 20)
  @NotBlank(message = "Customer's Plate Number was not provided under Policy Entity.")
  private String plateNumber;

  @Column(name = "chassis_number", nullable = false, length = 50)
  @NotBlank(message = "Customer's Chassis Number was not provided under Policy Entity.")
  private String chassisNumber;

  // @Column(name = "premium_amount", nullable = false, precision = 10, scale = 2)
  // @NotNull(message = "Policy's amount was not provided.")
  // private double premiumAmount;

  @Column(name = "sale_timestamp", nullable = false)
  @NotNull(message = "Policy's date and time was not provided.")
  private LocalDateTime saleTimestamp;

  @Column(name = "insurer_transaction_reference", nullable = true, length = 50)
  @NotBlank(message = "Insurer's Transaction Reference was not provided under Policy Entity.")
  private String insurerTransactionReference;

  /** Protecting the Policy Entity default Class Constructor. */
  protected Policy() {}

  /** Class constructor of Policy Entity. */
  public Policy(
      String policyId,
      CertificateOfCoverage certificateOfCoverage,
      Agent agent,
      String customerName,
      String plateNumber,
      String chassisNumber,
      // double premiumAmount,
      String insurerTransactionReference) {
    this.policyId = policyId;
    this.certificateOfCoverage = certificateOfCoverage;
    this.agent = agent;
    this.customerName = customerName;
    this.plateNumber = plateNumber;
    this.chassisNumber = chassisNumber;
    // this.premiumAmount = premiumAmount;
    this.insurerTransactionReference = insurerTransactionReference;

    this.saleTimestamp = LocalDateTime.now();
  }
}
