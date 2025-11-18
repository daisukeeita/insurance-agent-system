package com.acolyptos.insurance.domain.inventory;

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

/** An Entity that represents the details of the Certificate of Coverage. */
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
  private Status status;

  @OneToOne
  @Null
  @JoinColumn(name = "policy_id", referencedColumnName = "policy_id")
  private Policy policy;

  /** Protecting the default class constructor. */
  protected CertificateOfCoverage() {}

  /** Constructor to be used for the class. */
  public CertificateOfCoverage(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setCocNumber(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setProcuredBy(Agent procuredBy) {
    this.procuredBy = procuredBy;
  }

  public void setBatchReference(String batchReference) {
    this.batchReference = batchReference;
  }

  public void setDateIssued(LocalDate dateIssued) {
    this.dateIssued = dateIssued;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setPolicy(Policy policy) {
    this.policy = policy;
  }

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

  public Status getStatus() {
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
