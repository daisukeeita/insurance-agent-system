package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.VehicleService;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import com.acolyptos.insurance.domain.vehicle.VehicleRequestDto;
import com.acolyptos.insurance.domain.vehicle.VehicleResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

  private final VehicleService vehicleService;

  public VehicleController(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @PostMapping("/retrieveVehicle")
  public Mono<ResponseEntity<SuccessResponse<VehicleResponseDto>>> findAndRetrieveVehicle(
      @RequestBody @Valid VehicleRequestDto vehicleRequestDto) {

    return vehicleService
        .getVehicleInformation(vehicleRequestDto)
        .map(
            vehicleResponseDto ->
                new SuccessResponse<VehicleResponseDto>(
                    HttpStatus.OK.value(),
                    HttpStatus.OK,
                    "Successfully found the vehicle from LTMS.",
                    vehicleResponseDto))
        .map(successBody -> ResponseEntity.ok(successBody))
        .defaultIfEmpty(
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    new SuccessResponse<>(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND,
                        "No motor vehicle was found in LTMS or Legacy Data.",
                        null)));
  }
}
