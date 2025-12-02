package com.acolyptos.insurance.domain.certificate;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO class for Certificate of Coverage entity.
 *
 * <p>This DTO is typically used to carry the necessary information from a client request to
 * register or update a certificate of coverage data.
 */
public class CertificateRequestDto {

  @NotBlank(message = "Certificate of Coverage Number is required before saving.")
  private String cocNumber;

  @NotBlank(message = "Agent ID is required before saving the Certificate of Coverage.")
  private String agentId;

  @NotBlank(
      message = "Batch Reference Number is required before saving the Certificate of Coverage.")
  private String batchReference;

  @NotBlank(message = "Date Issued is required before saving the Certificate of Coverage.")
  private String dateIssued;

  /** Constructs a new {@code CertificateRequestDto}. */
  public CertificateRequestDto() {}

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
