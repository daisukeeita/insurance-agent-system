package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InsurerService;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRegisterRequest;
import com.acolyptos.insurance.domain.response.ErrorResponse;
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

/** Rest controller class that accepts requests from the client and process the request bodies. */
@RestController
@RequestMapping("/api/v1/insurer")
public class InsurerController {

  private final InsurerService insurerService;

  /**
   * Constructor of the insurer controller class.
   *
   * @param insurerService injected insurer service class to be able to use its methods for
   *     processing requests.
   */
  public InsurerController(final InsurerService insurerService) {
    this.insurerService = insurerService;
  }

  /**
   * Method for accepting the {@link InsurerRegisterRequest} from the client and pass it to a
   * dedicated service method to create the {@link Insurer} entity and save it to the databse.
   *
   * @param insurerRegisterRequest the accepted request body from the client.
   * @return {@link SuccessResponse} JSON body to the client if the process is successful,
   * @throws ErrorResponse JSON body to the client if the process fails.
   */
  @PostMapping("/createInsurer")
  public SuccessResponse<Insurer> createAndSaveInsurer(
      @RequestBody @Valid final InsurerRegisterRequest insurerRegisterRequest) {
    final Insurer insurer = insurerService.createInsurer(insurerRegisterRequest);

    final SuccessResponse<Insurer> apiResponse =
        new SuccessResponse<Insurer>(
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED,
            "Insurer successfully created.",
            insurer);

    return apiResponse;
  }

  /**
   * Method for accepting the string from the client and pass it to a dedicated service method to
   * find an {@link Insurer} entity from the database.
   *
   * @param insurerName the accepted request body/string from the client.
   * @return {@link SuccessResponse} JSON body to the client if the process is successful.
   * @throws ErrorResponse JSON body to the client if the process fails
   */
  @GetMapping("/getInsurer/{insurerName}")
  public SuccessResponse<Insurer> findAndGetInsurer(
      @PathVariable("insurerName") final String insurerName) {

    final Insurer insurer = insurerService.getInsurerByName(insurerName);

    final SuccessResponse<Insurer> apiResponse =
        new SuccessResponse<Insurer>(
            HttpStatus.OK.value(), HttpStatus.OK, "Found an Insurer using " + insurerName, insurer);

    return apiResponse;
  }

  @GetMapping("/getAllInsurers")
  public List<Insurer> getAllInsurers() {
    return insurerService.getAllInsurer();
  }
}
