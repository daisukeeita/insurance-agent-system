package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InsurerService;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRegisterRequest;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/insurer")
public class InsurerController {

  private final InsurerService insurerService;

  public InsurerController(InsurerService insurerService) {
    this.insurerService = insurerService;
  }

  @PostMapping("/createInsurer")
  public SuccessResponse<Insurer> createAndSaveInsurer(
      @RequestBody @Valid InsurerRegisterRequest insurerRegisterRequest) {
    Insurer insurer = insurerService.createInsurer(insurerRegisterRequest);

    SuccessResponse<Insurer> apiResponse =
        new SuccessResponse<Insurer>(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED,
            "Insurer successfully created.",
            insurer);

    return apiResponse;
  }

  @GetMapping("/getInsurer/{insurerName}")
  public SuccessResponse<Insurer> findAndGetInsurer(
      @PathVariable("insurerName") String insurerName) {

    Insurer insurer = insurerService.getInsurerByName(insurerName);

    SuccessResponse<Insurer> apiResponse =
        new SuccessResponse<Insurer>(
            HttpStatus.OK.value(), HttpStatus.OK, "Found an Insurer using " + insurerName, insurer);

    return apiResponse;
  }

  @GetMapping("/getAllInsurers")
  public List<Insurer> getAllInsurers() {
    return insurerService.getAllInsurer();
  }
}
