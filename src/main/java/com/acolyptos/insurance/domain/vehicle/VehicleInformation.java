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
}
