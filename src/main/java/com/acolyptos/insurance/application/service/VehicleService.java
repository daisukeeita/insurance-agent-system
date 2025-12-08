package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.exceptions.ExternalServiceException;
import com.acolyptos.insurance.domain.vehicle.Vehicle;
import com.acolyptos.insurance.domain.vehicle.VehicleRequestDto;
import com.acolyptos.insurance.domain.vehicle.VehicleResponseDto;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class VehicleService {

  private final WebClient webClient;

  @Value("${api.thirdparty.parameters}")
  private String apiParameters;

  public VehicleService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<VehicleResponseDto> getVehicleInformation(VehicleRequestDto vehicleRequestDto) {

    return webClient
        .post()
        // .uri(apiParameters)
        .bodyValue(vehicleRequestDto)
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            response -> {
              HttpStatus resolvedStatus = HttpStatus.valueOf(response.statusCode().value());

              return response
                  .bodyToMono(String.class)
                  .flatMap(
                      body ->
                          Mono.error(
                              new ExternalServiceException(
                                  "No motor vehicle was found in LTMS or Legacy Data. (Service)",
                                  resolvedStatus,
                                  body)));
            })
        .bodyToMono(Vehicle.class)
        .map(this::mapVehicleToDto)
        .onErrorResume(
            error -> {
              if (error instanceof WebClientException || error instanceof UnknownHostException) {
                new ExternalServiceException(
                    "The LTMS is unavailable or offline.", HttpStatus.SERVICE_UNAVAILABLE, null);
              }
              return Mono.error(error);
            });
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
