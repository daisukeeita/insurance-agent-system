package com.acolyptos.insurance.domain.transaction;

public class PolicyResponseDto {

  private String policyId;

  private String cocNumber;

  private String agentName;

  private String customerName;

  private String plateNumber;

  private String chassisNumber;

  private String premiumAmount;

  private String saleTimestamp;

  private String insurerBindingTransactionReference;

  protected PolicyResponseDto() {}

  public PolicyResponseDto(
      String policyId,
      String cocNumber,
      String agentName,
      String customerName,
      String plateNumber,
      String chassisNumber,
      String premiumAmount,
      String saleTimestamp,
      String insurerBindingTransactionReference) {
    this.policyId = policyId;
    this.cocNumber = cocNumber;
    this.agentName = agentName;
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

  public void setCocNumber(String cocNumber) {
    this.cocNumber = cocNumber;
  }

  public void setAgentName(String agentName) {
    this.agentName = agentName;
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

  public void setSaleTimestamp(String saleTimestamp) {
    this.saleTimestamp = saleTimestamp;
  }

  public void setPremiumAmount(String premiumAmount) {
    this.premiumAmount = premiumAmount;
  }

  public void setInsurerBindingTransactionReference(String insurerBindingTransactionReference) {
    this.insurerBindingTransactionReference = insurerBindingTransactionReference;
  }

  public String getPolicyId() {
    return policyId;
  }

  public String getCocNumber() {
    return cocNumber;
  }

  public String getAgentName() {
    return agentName;
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

  public String getSaleTimestamp() {
    return saleTimestamp;
  }

  public String getPremiumAmount() {
    return premiumAmount;
  }

  public String getInsurerBindingTransactionReference() {
    return insurerBindingTransactionReference;
  }
}
