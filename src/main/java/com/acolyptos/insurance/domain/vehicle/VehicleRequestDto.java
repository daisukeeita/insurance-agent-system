package com.acolyptos.insurance.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Request DTO class for third-party API request. */
public class VehicleRequestDto {

  @JsonProperty("chassis_no")
  private String chassis_no;

  @JsonProperty("engine_no")
  private String engine_no;

  @JsonProperty("vin_no")
  private String vin_no;

  @JsonProperty("plate_no")
  private String plate_no;

  @JsonProperty("mv_file_no")
  private String mv_file_no;

  /** Constructs a new {@code VehicleRequestDto}. */
  public VehicleRequestDto() {}

  public void setChassisNo(String chassis_no) {
    this.chassis_no = chassis_no;
  }

  public void setEngineNo(String engine_no) {
    this.engine_no = engine_no;
  }

  public void setVinNo(String vin_no) {
    this.vin_no = vin_no;
  }

  public void setPlateNo(String plate_no) {
    this.plate_no = plate_no;
  }

  public void setMvFileNo(String mv_file_no) {
    this.mv_file_no = mv_file_no;
  }

  public String getChassisNo() {
    return chassis_no;
  }

  public String getEngineNo() {
    return engine_no;
  }

  public String getVinNo() {
    return vin_no;
  }

  public String getPlateNo() {
    return plate_no;
  }

  public String getMvFileNo() {
    return mv_file_no;
  }

  @Override
  public String toString() {
    return "VehicleRequestDto {\n\tchassis_no: "
        + chassis_no
        + ",\n\tengine_no: "
        + engine_no
        + ",\n\tvin_no: "
        + vin_no
        + ",\n\tplate_no: "
        + plate_no
        + ",\n\tmv_file_no: "
        + mv_file_no
        + "\n}";
  }
}
