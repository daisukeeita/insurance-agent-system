package com.acolyptos.insurance.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleInformation {

  @JsonProperty("Historic_Vehicle")
  private String historicVehicle;

  @JsonProperty("Equivalent_Inertia")
  private int equivalentInertia;

  @JsonProperty("Fuel_Type")
  private String fuelType;

  @JsonProperty("Traction_Type")
  private String tractionType;

  @JsonProperty("Number_Of_Axes")
  private int numberOfAxes;

  @JsonProperty("Model_Year")
  private String modelYear;

  @JsonProperty("Engine_Capacity")
  private int engineCapacity;

  @JsonProperty("Category")
  private String category;

  @JsonProperty("Category_Type")
  private String categoryType;

  @JsonProperty("Chassis")
  private String chassis;

  @JsonProperty("Engine")
  private String engine;

  @JsonProperty("MV_File_No")
  private String mvFileNumber;

  @JsonProperty("Circulation_Date")
  private String circulationDate;

  @JsonProperty("Color")
  private String color;

  @JsonProperty("License_Plate")
  private String plateNumber;

  @JsonProperty("Manufacturer")
  private String manufacturer;

  @JsonProperty("Brand")
  private String brand;

  @JsonProperty("Mileage")
  private int mileage;

  @JsonProperty("VIN")
  private String vin;

  @JsonProperty("Turbo")
  private String turbo;

  @JsonProperty("Presence_Of_Catalytic_Converter")
  private String presenceOfCatalyticConverter;

  @JsonProperty("Maximum_Total_Weight")
  private int maximumTotalWeight;

  @JsonProperty("Date_First_Registration")
  private String dateFirstRegistration;

  protected VehicleInformation() {}

  public VehicleInformation(
      String historicVehicle,
      int equivalentInertia,
      String fuelType,
      String tractionType,
      int numberOfAxes,
      String modelYear,
      int engineCapacity,
      String category,
      String categoryType,
      String chassis,
      String engine,
      String mvFileNumber,
      String circulationDate,
      String color,
      String plateNumber,
      String manufacturer,
      String brand,
      int mileage,
      String vin,
      String turbo,
      String presenceOfCatalyticConverter,
      int maximumTotalWeight,
      String dateFirstRegistration) {
    this.historicVehicle = historicVehicle;
    this.equivalentInertia = equivalentInertia;
    this.fuelType = fuelType;
    this.tractionType = tractionType;
    this.numberOfAxes = numberOfAxes;
    this.modelYear = modelYear;
    this.engineCapacity = engineCapacity;
    this.category = category;
    this.categoryType = categoryType;
    this.chassis = chassis;
    this.engine = engine;
    this.mvFileNumber = mvFileNumber;
    this.circulationDate = circulationDate;
    this.color = color;
    this.plateNumber = plateNumber;
    this.manufacturer = manufacturer;
    this.brand = brand;
    this.mileage = mileage;
    this.vin = vin;
    this.turbo = turbo;
    this.presenceOfCatalyticConverter = presenceOfCatalyticConverter;
    this.maximumTotalWeight = maximumTotalWeight;
    this.dateFirstRegistration = dateFirstRegistration;
  }

  public void setHistoricVehicle(String historicVehicle) {
    this.historicVehicle = historicVehicle;
  }

  public void setEquivalentInertia(int equivalentInertia) {
    this.equivalentInertia = equivalentInertia;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public void setTractionType(String tractionType) {
    this.tractionType = tractionType;
  }

  public void setNumberOfAxes(int numberOfAxes) {
    this.numberOfAxes = numberOfAxes;
  }

  public void setModelYear(String modelYear) {
    this.modelYear = modelYear;
  }

  public void setEngineCapacity(int engineCapacity) {
    this.engineCapacity = engineCapacity;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setCategoryType(String categoryType) {
    this.categoryType = categoryType;
  }

  public void setChassis(String chassis) {
    this.chassis = chassis;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public void setMvFileNumber(String mvFileNumber) {
    this.mvFileNumber = mvFileNumber;
  }

  public void setCirculationDate(String circulationDate) {
    this.circulationDate = circulationDate;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public void setTurbo(String turbo) {
    this.turbo = turbo;
  }

  public void setPresenceOfCatalyticConverter(String presenceOfCatalyticConverter) {
    this.presenceOfCatalyticConverter = presenceOfCatalyticConverter;
  }

  public void setMaximumTotalWeight(int maximumTotalWeight) {
    this.maximumTotalWeight = maximumTotalWeight;
  }

  public void setDateFirstRegistration(String dateFirstRegistration) {
    this.dateFirstRegistration = dateFirstRegistration;
  }

  public String getHistoricVehicle() {
    return historicVehicle;
  }

  public int getEquivalentIntertia() {
    return equivalentInertia;
  }

  public String getFuelType() {
    return fuelType;
  }

  public String getTractionType() {
    return tractionType;
  }

  public int getNumberOfAxes() {
    return numberOfAxes;
  }

  public String getModelYear() {
    return modelYear;
  }

  public int getEngineCapcity() {
    return engineCapacity;
  }

  public String getCategory() {
    return category;
  }

  public String getCategoryType() {
    return categoryType;
  }

  public String getChassis() {
    return chassis;
  }

  public String getEngine() {
    return engine;
  }

  public String getMvFileNumber() {
    return mvFileNumber;
  }

  public String getCirculationDate() {
    return circulationDate;
  }

  public String getColor() {
    return color;
  }

  public String getPlateNumber() {
    return plateNumber;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getBrand() {
    return brand;
  }

  public int getMileage() {
    return mileage;
  }

  public String getVin() {
    return vin;
  }

  public String getTurbo() {
    return turbo;
  }

  public String getPresenceOfCatalyticConverter() {
    return presenceOfCatalyticConverter;
  }

  public int getMaximumTotalWeight() {
    return maximumTotalWeight;
  }

  public String getDateFirstRegistration() {
    return dateFirstRegistration;
  }
}
