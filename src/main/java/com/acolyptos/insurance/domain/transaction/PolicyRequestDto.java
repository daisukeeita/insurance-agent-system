package com.acolyptos.insurance.domain.transaction;

import jakarta.validation.constraints.NotBlank;

public class PolicyRequestDto {

  @NotBlank(message = "Policy ID was not provided from the request.")
  private String policyId;

  @NotBlank(message = "Certificate of Coverage number was not provided from the request.")
  private String cocNumber;

  @NotBlank(message = "Agent username was not provided from the request.")
  private String agentUsername;

  @NotBlank(message = "Customer's First Name was not provided from the request.")
  private String customerFirstName;

  @NotBlank(message = "Customer's Last Name was not provided from the request.")
  private String customerLastName;

  private String customerMiddleName;

  @NotBlank(message = "Customer's plate number was not provided from the request.")
  private String plateNumber;

  @NotBlank(message = "Customer's chassis number was not provided from the request.")
  private String chassisNumber;

  @NotBlank(message = "Premium Amount was not provided from the request.")
  private String premiumAmount;

  @NotBlank(message = "Sales date and time stamp was not provided from the request.")
  private String saleTimestamp;

  @NotBlank(message = "Insurer's Binding Transaction Reference was not provided from the request.")
  private String insurerBindingTransactionReference;

  public PolicyRequestDto(
      String policyId,
      String cocNumber,
      String agentUsername,
      String customerFirstName,
      String customerMiddleName,
      String customerLastName,
      String plateNumber,
      String chassisNumber,
      String premiumAmount,
      String saleTimestamp,
      String insurerBindingTransactionReference) {
    this.policyId = policyId;
    this.cocNumber = cocNumber;
    this.agentUsername = agentUsername;
    this.customerFirstName = customerFirstName;
    this.customerMiddleName = customerMiddleName;
    this.customerLastName = customerLastName;
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

  public void setAgentUsername(String agentUsername) {
    this.agentUsername = agentUsername;
  }

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public void setCustomerMiddleName(String customerMiddleName) {
    this.customerMiddleName = customerMiddleName;
  }

  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public void setChassisNumber(String chassisNumber) {
    this.chassisNumber = chassisNumber;
  }

  public void setPremiumAmount(String premiumAmount) {
    this.premiumAmount = premiumAmount;
  }

  public void setSaleTimestamp(String saleTimestamp) {
    this.saleTimestamp = saleTimestamp;
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

  public String getAgentUsername() {
    return agentUsername;
  }

  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public String getCustomerMiddleName() {
    return customerMiddleName;
  }

  public String getCustomerLastName() {
    return customerLastName;
  }

  public String getCustomerFullName() {
    if (customerMiddleName == null || customerMiddleName.trim().isEmpty()) {
      return customerFirstName + " " + customerLastName;
    }

    return customerFirstName + " " + customerMiddleName + " " + customerLastName;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public String getChassisNumber() {
    return chassisNumber;
  }

  public String getPremiumAmount() {
    return premiumAmount;
  }

  public String getSaleTimestamp() {
    return saleTimestamp;
  }

  public String getInsurerBindingTransactionReference() {
    return insurerBindingTransactionReference;
  }
}
