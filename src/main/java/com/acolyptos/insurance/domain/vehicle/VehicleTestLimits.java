package com.acolyptos.insurance.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleTestLimits {

  @JsonProperty("light_intensity")
  private String lightIntensity;

  @JsonProperty("brake_service_eff")
  private String brakeServiceEff;

  @JsonProperty("brake_parking_eff")
  private String brakeParkingEff;

  @JsonProperty("brake_parking_diff")
  private String brakeServiceDiff;

  @JsonProperty("sideslip_deviation")
  private String sideslipDeviation;

  @JsonProperty("suspension_deviation")
  private String suspensionDeviation;

  @JsonProperty("speed_deviation")
  private String speedDeviation;

  @JsonProperty("sound_level")
  private String soundLevel;

  @JsonProperty("emission_hc")
  private String emissionHc;

  @JsonProperty("emission_co")
  private String emissionCo;

  @JsonProperty("opacity_k")
  private String opacityK;

  protected VehicleTestLimits() {}

  public VehicleTestLimits(
      String lightIntensity,
      String brakeServiceEff,
      String brakeParkingEff,
      String brakeServiceDiff,
      String sideslipDeviation,
      String suspensionDeviation,
      String speedDeviation,
      String soundLevel,
      String emissionHc,
      String emissionCo,
      String opacityK) {
    this.lightIntensity = lightIntensity;
    this.brakeServiceEff = brakeServiceEff;
    this.brakeParkingEff = brakeParkingEff;
    this.brakeServiceDiff = brakeServiceDiff;
    this.sideslipDeviation = sideslipDeviation;
    this.suspensionDeviation = suspensionDeviation;
    this.speedDeviation = speedDeviation;
    this.soundLevel = soundLevel;
    this.emissionHc = emissionHc;
    this.emissionCo = emissionCo;
    this.opacityK = opacityK;
  }
}
