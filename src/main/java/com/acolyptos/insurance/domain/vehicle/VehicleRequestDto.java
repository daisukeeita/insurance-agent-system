package com.acolyptos.insurance.domain.vehicle;

/** Request DTO class for third-party API request. */
public class VehicleRequestDto {

  private String chassis_no;
  private String engine_no;
  private String vin_no;
  private String plate_no;
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
}
