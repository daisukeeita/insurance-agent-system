package com.acolyptos.insurance.domain.inventory;

import jakarta.validation.constraints.NotBlank;

/** Class that represents the request body of the client for Certificate of Coverage. */
public class CocRequestBody {

  @NotBlank(message = "Certificate of Coverage Number is required before saving.")
  private String cocNumber;

  @NotBlank(message = "Agent ID is required before saving the Certificate of Coverage.")
  private String agentId;

  @NotBlank(
      message = "Batch Reference Number is required before saving the Certificate of Coverage.")
  private String batchReference;

  @NotBlank(message = "Date Issued is required before saving the Certificate of Coverage.")
  private String dateIssued;

  /** Default constructor of the class. */
  public CocRequestBody() {}

  public void setCocNumber(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public void setBatchReference(String batchReference) {
    this.batchReference = batchReference;
  }

  public void setDateIssued(String dateIssued) {
    this.dateIssued = dateIssued;
  }

  public String getCocNumber() {
    return cocNumber;
  }

  public String getAgentId() {
    return agentId;
  }

  public String getBatchReference() {
    return batchReference;
  }

  public String getDateIssued() {
    return dateIssued;
  }
}
