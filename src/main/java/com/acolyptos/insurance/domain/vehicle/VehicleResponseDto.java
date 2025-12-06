package com.acolyptos.insurance.domain.vehicle;

/**
 * Response DTO class for third-party API response.
 *
 * <p>This DTO is typically used to carry the necessary information that will be sent to the client.
 */
public class VehicleResponseDto {

  private String lastInspectionId;
  private String mvFileNumber;
  private String plateNumber;
  private String chassisNumber;
  private String engineNumber;
  private String brand;
  private String manufacturer;
  private String fuelType;
  private String categoryType;
  private String color;
  private String engineCapacity;
  private String maximumTotalWeight;
  private String modelYear;
  private String firstRegistrationDate;

  /** Constructs a new {@code VehicleResponseDto}. */
  public VehicleResponseDto() {}

  /**
   * Sets the inspection ID of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param lastInspectionId The last inspection ID of the vehicle to be sent.
   */
  public void setLastInspectionId(final String lastInspectionId) {
    if (lastInspectionId == null || lastInspectionId == "NA" || lastInspectionId.trim().isEmpty()) {
      this.lastInspectionId = "Inspection ID was not provided.";
    }

    this.lastInspectionId = lastInspectionId;
  }

  /**
   * Sets the MV File Number of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param mvFileNumber The MV File Number of the vehicle to be sent.
   */
  public void setMvFileNumber(final String mvFileNumber) {
    if (mvFileNumber == null || mvFileNumber == "NA" || mvFileNumber.trim().isEmpty()) {
      this.mvFileNumber = "MV File Number was not provided.";
    }

    this.mvFileNumber = mvFileNumber;
  }

  /**
   * Sets the Plate Number of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param plateNumber The plate number of the vehicle to be sent.
   */
  public void setPlateNumber(final String plateNumber) {
    if (plateNumber == null || plateNumber == "NA" || plateNumber.trim().isEmpty()) {
      this.plateNumber = "Plate Number was not provided.";
    }

    this.plateNumber = plateNumber;
  }

  /**
   * Sets the Chassis Number of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param chassisNumber The chassis number of the vehicle to be sent.
   */
  public void setChassisNumber(final String chassisNumber) {
    if (chassisNumber == null || chassisNumber == "NA" || chassisNumber.trim().isEmpty()) {
      this.chassisNumber = "Chassis Number was not provided.";
    }

    this.chassisNumber = chassisNumber;
  }

  /**
   * Sets the Engine Number of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param engineNumber The engine number of the vehicle to be sent.
   */
  public void setEngineNumber(final String engineNumber) {
    if (engineNumber == null || engineNumber == "NA" || engineNumber.trim().isEmpty()) {
      this.engineNumber = "Engine Number was not provided.";
    }

    this.engineNumber = engineNumber;
  }

  /**
   * Sets the Brand of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param brand The brand of the vehicle to be sent.
   */
  public void setBrand(final String brand) {
    if (brand == null || brand == "NA" || brand.trim().isEmpty()) {
      this.brand = "Vehicle's Brand was not provided.";
    }

    this.brand = brand;
  }

  /**
   * Sets the Manufacturer of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param manufacturer The manufacturer of the vehicle to be sent.
   */
  public void setManufacturer(final String manufacturer) {
    if (manufacturer == null || manufacturer == "NA" || manufacturer.trim().isEmpty()) {
      this.manufacturer = "Vehicle's Manufacturer was not provided.";
    }

    this.manufacturer = manufacturer;
  }

  /**
   * Sets the Fuel Type of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param fuelType The fuel type of the vehicle to be sent.
   */
  public void setFuelType(final String fuelType) {
    if (fuelType == null || fuelType == "NA" || fuelType.trim().isEmpty()) {
      this.fuelType = "Vehicle's Fuel Type was not provided.";
    }

    this.fuelType = fuelType;
  }

  /**
   * Sets the Category Type of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param categoryType The category type of the vehicle to be sent.
   */
  public void setCategoryType(final String categoryType) {
    if (categoryType == null || categoryType == "NA" || categoryType.trim().isEmpty()) {
      this.categoryType = "Vehicle's Category Type was not provided.";
    }

    this.categoryType = categoryType;
  }

  /**
   * Sets the Color of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param color The color of the vehicle to be sent.
   */
  public void setColor(final String color) {
    if (color == null || color == "NA" || color.trim().isEmpty()) {
      this.color = "Vehicle's Color was not provided.";
    }

    this.color = color;
  }

  /**
   * Sets the Engine Capacity of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param engineCapacity The engine capacity of the vehicle to be sent.
   */
  public void setEngineCapacity(final String engineCapacity) {
    if (engineCapacity == null || engineCapacity == "NA" || engineCapacity.trim().isEmpty()) {
      this.engineCapacity = "Vehicle's Engine Capacity was not provided.";
    }

    this.engineCapacity = engineCapacity;
  }

  /**
   * Sets the Maximum Total Weight of the Vehicle (The maximum weight with passengers and cargos).
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param maximumTotalWeight The maximum total weight of the vehicle to be sent.
   */
  public void setMaximumTotalWeight(final String maximumTotalWeight) {
    if (maximumTotalWeight == null
        || maximumTotalWeight == "NA"
        || maximumTotalWeight.trim().isEmpty()) {
      this.maximumTotalWeight = "Vehicle's Maximum Total Weight was not provided.";
    }

    this.maximumTotalWeight = maximumTotalWeight;
  }

  /**
   * Sets the Model Year of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param modelYear The model year of the vehicle to be sent.
   */
  public void setModelYear(final String modelYear) {
    if (modelYear == null || modelYear == "NA" || modelYear.trim().isEmpty()) {
      this.modelYear = "Vehicle's Model Year was not provided.";
    }

    this.modelYear = modelYear;
  }

  /**
   * Sets the First Registration Date of the Vehicle.
   *
   * <p>It also checks for null, NA, or empty value before setting it up.
   *
   * @param firstRegistrationDate The registration date of the vehicle to be sent.
   */
  public void setFirstRegistrationDate(final String firstRegistrationDate) {
    if (firstRegistrationDate == null
        || firstRegistrationDate == "NA"
        || firstRegistrationDate.trim().isEmpty()) {
      this.firstRegistrationDate = "Vehicle's First Registration Date was not provided.";
    }

    this.firstRegistrationDate = firstRegistrationDate;
  }

  public String getLastInspectionId() {
    return lastInspectionId;
  }

  public String getMvFileNumber() {
    return mvFileNumber;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public String getChassisNumber() {
    return chassisNumber;
  }

  public String getEngineNumber() {
    return engineNumber;
  }

  public String getBrand() {
    return brand;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getFuelType() {
    return fuelType;
  }

  public String getCategoryType() {
    return categoryType;
  }

  public String getColor() {
    return color;
  }

  public String getEngineCapacity() {
    return engineCapacity;
  }

  public String getMaximumTotalWeight() {
    return maximumTotalWeight;
  }

  public String getModelYear() {
    return modelYear;
  }

  public String getFirstRegistrationDate() {
    return firstRegistrationDate;
  }
}
