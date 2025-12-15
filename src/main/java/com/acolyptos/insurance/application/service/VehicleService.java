package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.vehicle.Vehicle;
import com.acolyptos.insurance.domain.vehicle.VehicleRequestDto;
import com.acolyptos.insurance.domain.vehicle.VehicleResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class VehicleService {

  private final RestClient restClient;

  @Value("${api.thirdparty.parameters}")
  private String apiParameters;

  public VehicleService(RestClient restClient) {
    this.restClient = restClient;
  }

  public VehicleResponseDto getVehicleInformation(VehicleRequestDto vehicleRequestDto) {

    ResponseEntity<Vehicle> responseEntity =
        restClient.post().body(vehicleRequestDto).retrieve().toEntity(Vehicle.class);

    return mapVehicleToDto(responseEntity.getBody());
  }

  private VehicleResponseDto mapVehicleToDto(Vehicle vehicle) {
    VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
    vehicleResponseDto.setLastInspectionId(vehicle.getInspectionId());
    vehicleResponseDto.setMvFileNumber(vehicle.getVehicleInformation().getMvFileNumber());
    vehicleResponseDto.setPlateNumber(vehicle.getVehicleInformation().getPlateNumber());
    vehicleResponseDto.setChassisNumber(vehicle.getVehicleInformation().getChassis());
    vehicleResponseDto.setEngineNumber(vehicle.getVehicleInformation().getEngine());
    vehicleResponseDto.setBrand(vehicle.getVehicleInformation().getBrand());
    vehicleResponseDto.setManufacturer(vehicle.getVehicleInformation().getManufacturer());
    vehicleResponseDto.setFuelType(vehicle.getVehicleInformation().getFuelType());
    vehicleResponseDto.setCategoryType(vehicle.getVehicleInformation().getCategoryType());
    vehicleResponseDto.setColor(vehicle.getVehicleInformation().getColor());
    vehicleResponseDto.setModelYear(vehicle.getVehicleInformation().getModelYear());
    vehicleResponseDto.setEngineCapacity(
        String.valueOf(vehicle.getVehicleInformation().getEngineCapcity()));
    vehicleResponseDto.setMaximumTotalWeight(
        String.valueOf(vehicle.getVehicleInformation().getMaximumTotalWeight()));
    vehicleResponseDto.setFirstRegistrationDate(
        vehicle.getVehicleInformation().getDateFirstRegistration());

    return vehicleResponseDto;
  }
}
