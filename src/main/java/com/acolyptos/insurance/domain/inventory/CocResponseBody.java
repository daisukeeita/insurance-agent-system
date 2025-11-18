package com.acolyptos.insurance.domain.inventory;

public class CocResponseBody {

  private String cocNumber;
  private String procuredBy;
  private String batchReference;
  private String dateIssued;
  private String status;

  protected CocResponseBody() {}

  public CocResponseBody(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setProcuredBy(String agentId) {
    this.procuredBy = agentId;
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
