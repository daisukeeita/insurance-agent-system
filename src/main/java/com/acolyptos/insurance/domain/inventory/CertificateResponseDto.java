package com.acolyptos.insurance.domain.inventory;

/**
 * Response DTO class for Certificate of Coverage entity.
 *
 * <p>This DTO is typically used to carry necessary information that will be sent to the client.
 */
public class CertificateResponseDto {

  private String cocNumber;
  private String procuredBy;
  private String batchReference;
  private String dateIssued;
  private String status;

  /** Constructs a new {@code CertificateResponseDto}. */
  public CertificateResponseDto() {}

  public void setCocNumber(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setProcuredBy(String agentName) {
    this.procuredBy = agentName;
  }

  public void setBatchReference(String batchReference) {
    this.batchReference = batchReference;
  }

  public void setDateIssued(String dateIssued) {
    this.dateIssued = dateIssued;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCocNumber() {
    return cocNumber;
  }

  public String getProcuredBy() {
    return procuredBy;
  }

  public String getBatchReference() {
    return batchReference;
  }

  public String getDateIssued() {
    return dateIssued;
  }

  public String getStatus() {
    return status;
  }
}
