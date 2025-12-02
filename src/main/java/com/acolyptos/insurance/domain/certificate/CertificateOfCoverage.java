package com.acolyptos.insurance.domain.certificate;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.transaction.Policy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.time.LocalDate;

/**
 * Represents the Certificate of Coverage entity stored in the "coc_inventory" database table. This
 * entity holds the core details aboout the coverage of the vehicle insurance.
 */
@Entity
@Table(name = "coc_inventory")
public class CertificateOfCoverage {

  @Id
  @Column(
      name = "coc_number",
      nullable = false,
      length = 50,
      unique = true,
      insertable = true,
      updatable = false)
  @NotBlank(message = "Certificate of Coverage's number was not provided.")
  private String cocNumber;

  @ManyToOne
  @JoinColumn(name = "procured_by", referencedColumnName = "agent_id", nullable = false)
  @NotNull(message = "Agent's information was not provided under Certificate of Coverage entity.")
  private Agent procuredBy;

  @Column(name = "batch_reference", nullable = false, length = 50)
  @NotBlank(message = "Certificate of Coverage's batch reference was not provided.")
  private String batchReference;

  @Column(name = "date_issued", nullable = false)
  @NotNull(message = "Certificate of Coverage's issued date was not provided.")
  private LocalDate dateIssued;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private CertificateStatus status;

  @OneToOne
  @Null
  @JoinColumn(name = "policy_id", referencedColumnName = "policy_id", nullable = true)
  private Policy policy;

  /**
   * Default constructor required by the JPA/Hibernate. Protected to prevent direct instantiation.
   */
  protected CertificateOfCoverage() {}

  /**
   * Constructs a new {@code CertificateOfCoverage} entity with with required data.
   *
   * @param cocNumber The unique identification of the certificate of coverage, it must be unique.
   * @param procuredBy The agent requesting/procuring the certificate of coverage.
   * @param batchReference The reference number of procured certificate of coverage.
   * @param dateIssued The date issued of the certificate of coverage.
   * @param status The status of the certificate of coverage.
   */
  public CertificateOfCoverage(
      String cocNumber,
      Agent procuredBy,
      String batchReference,
      LocalDate dateIssued,
      CertificateStatus status) {
    this.cocNumber = cocNumber;
    this.procuredBy = procuredBy;
    this.batchReference = batchReference;
    this.dateIssued = dateIssued;
    this.status = status;
  }

  // public void setCocNumber(String cocNumber) {
  //   this.cocNumber = cocNumber;
  // }
  //
  // public void setProcuredBy(Agent procuredBy) {
  //   this.procuredBy = procuredBy;
  // }
  //
  // public void setBatchReference(String batchReference) {
  //   this.batchReference = batchReference;
  // }
  //
  // public void setDateIssued(LocalDate dateIssued) {
  //   this.dateIssued = dateIssued;
  // }
  //
  // public void setStatus(Status status) {
  //   this.status = status;
  // }
  //
  // public void setPolicy(Policy policy) {
  //   this.policy = policy;
  // }

  public String getCocNumber() {
    return cocNumber;
  }

  public Agent getProcuredBy() {
    return procuredBy;
  }

  public String getBatchReference() {
    return batchReference;
  }

  public LocalDate getDateIssued() {
    return dateIssued;
  }

  public CertificateStatus getStatus() {
    return status;
  }

  public Policy getPolicy() {
    return policy;
  }

  @Override
  public String toString() {
    return "CertificateOfCoverage {\n\tcocNumber: "
        + cocNumber
        + ",\n\tprocuredBy: "
        + procuredBy
        + ",\n\tbatchReference: "
        + batchReference
        + ",\n\tdateIssued: "
        + dateIssued
        + ",\n\tstatus: "
        + status
        + ",\n\tpolicy: "
        + policy
        + "\n}";
  }
}
