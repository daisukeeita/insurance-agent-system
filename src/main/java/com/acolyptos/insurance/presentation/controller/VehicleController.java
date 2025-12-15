package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.VehicleService;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import com.acolyptos.insurance.domain.vehicle.VehicleRequestDto;
import com.acolyptos.insurance.domain.vehicle.VehicleResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

  private final VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @PostMapping("/retrieveVehicle")
  public SuccessResponse<VehicleResponseDto> findAndRetrieveVehicle(
      @RequestBody @Valid VehicleRequestDto vehicleRequestDto) {

    VehicleResponseDto responseDto = vehicleService.getVehicleInformation(vehicleRequestDto);

    return new SuccessResponse<VehicleResponseDto>(
        HttpStatus.OK.value(),
        HttpStatus.OK,
        "Successfully found the vehicle data from LTMS.",
        responseDto);
  }
}
