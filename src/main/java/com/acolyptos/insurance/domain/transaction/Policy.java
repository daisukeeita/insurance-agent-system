package com.acolyptos.insurance.domain.transaction;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Policy stored in "policies" database table. This entity holds the core details about
 * the Agent's transaction receipt.
 */
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

  @Column(name = "premium_amount", nullable = false, precision = 6, scale = 2)
  @NotNull(message = "Policy's amount was not provided.")
  private BigDecimal premiumAmount;

  @Column(name = "sale_timestamp", nullable = false)
  @NotNull(message = "Policy's date and time was not provided.")
  private LocalDateTime saleTimestamp;

  @Column(name = "insurer_transaction_reference", nullable = true, length = 50)
  @NotBlank(message = "Insurer's Transaction Reference was not provided under Policy Entity.")
  private String insurerBindingTransactionReference;

  /**
   * Default constructor required by the JPA/Hibernate. Protected to prevent direct instantiation.
   */
  protected Policy() {}

  /**
   * Constructs a new Policy entity with required data.
   *
   * @param policyId The policy ID or its unique identification number of the policy.
   * @param certificateOfCoverage The coverage that will be used for this policy.
   * @param agent The agent processing the policy.
   * @param customerName The name of the customer to bind in the certificate.
   * @param plateNumber The plate number of the customer to bind in the certificate.
   * @param chassisNumber The chassis number of the customer to bind in the certificate.
   * @param premiumAmount The price of the policy.
   * @param saleTimestamp The date and timestamp of the processed policy.
   * @param insurerBindingTransactionReference The binding transaction reference number of the
   *     certificate and customoer, processed by the Insurer.
   */
  public Policy(
      String policyId,
      CertificateOfCoverage certificateOfCoverage,
      Agent agent,
      String customerName,
      String plateNumber,
      String chassisNumber,
      BigDecimal premiumAmount,
      LocalDateTime saleTimestamp,
      String insurerBindingTransactionReference) {
    this.policyId = policyId;
    this.certificateOfCoverage = certificateOfCoverage;
    this.agent = agent;
    this.customerName = customerName;
    this.plateNumber = plateNumber;
    this.chassisNumber = chassisNumber;
    this.premiumAmount = premiumAmount;
    this.saleTimestamp = saleTimestamp;
    this.insurerBindingTransactionReference = insurerBindingTransactionReference;
  }

  public void setPolicyId(String policyId) {
    this.policyId = policyId;
  }

  public void setCertificateOfCoverage(CertificateOfCoverage certificateOfCoverage) {
    this.certificateOfCoverage = certificateOfCoverage;
  }

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public void setChassisNumber(String chassisNumber) {
    this.chassisNumber = chassisNumber;
  }

  public void setPremiumAmount(BigDecimal premiumAmount) {
    this.premiumAmount = premiumAmount;
  }

  public void setTimestamp(LocalDateTime saleTimestamp) {
    this.saleTimestamp = saleTimestamp;
  }

  public void setInsurerBindingTransactionReference(String insurerBindingTransactionReference) {
    this.insurerBindingTransactionReference = insurerBindingTransactionReference;
  }

  public String getPolicyId() {
    return policyId;
  }

  public CertificateOfCoverage getCertificateOfCoverage() {
    return certificateOfCoverage;
  }

  public Agent getAgent() {
    return agent;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public String getChassisNumber() {
    return chassisNumber;
  }

  public BigDecimal getPremiumAmount() {
    return premiumAmount;
  }

  public LocalDateTime getSaleTimestamp() {
    return saleTimestamp;
  }

  public String getInsurerBindingTransactionReference() {
    return insurerBindingTransactionReference;
  }

  /** Converting Sale's Timestamp to String Type. */
  public String getConvertedSaleTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MMD-dd HH:mm");
    return saleTimestamp.format(formatter);
  }

  public String getAgentFullName() {
    return agent.getFullName();
  }

  public String getAttachedCocNumber() {
    return certificateOfCoverage.getCocNumber();
  }
}
