package com.acolyptos.insurance.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {

  @JsonProperty("Inspection_ID")
  private String inspectionId;

  @JsonProperty("Purpose")
  private String purpose;

  @JsonProperty("Vehicle_Information")
  private VehicleInformation vehicleInformation;

  @JsonProperty("Test_Limits")
  private VehicleTestLimits vehicleTestLimits;

  protected Vehicle() {}

  public Vehicle(
      String inspectionId,
      String purpose,
      VehicleInformation vehicleInformation,
      VehicleTestLimits vehicleTestLimits) {
    this.inspectionId = inspectionId;
    this.purpose = purpose;
    this.vehicleInformation = vehicleInformation;
    this.vehicleTestLimits = vehicleTestLimits;
  }

  public String getInspectionId() {
    return inspectionId;
  }

  public String getPurpoose() {
    return purpose;
  }

  public VehicleInformation getVehicleInformation() {
    return vehicleInformation;
  }

  public VehicleTestLimits getVehicleTestLimits() {
    return vehicleTestLimits;
  }
}
