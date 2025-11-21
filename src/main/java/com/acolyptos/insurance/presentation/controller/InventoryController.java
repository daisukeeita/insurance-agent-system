package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InventoryService;
import com.acolyptos.insurance.domain.inventory.CocRequestBody;
import com.acolyptos.insurance.domain.inventory.CocResponseBody;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @PostMapping("/addCertificateOfCoverage")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<CocResponseBody> processAndCreateCertificateOfCoverage(
      @RequestBody @Valid CocRequestBody cocRequestBody) {

    CocResponseBody certificateOfCoverage =
        inventoryService.createAndSaveCertificateOfCoverage(cocRequestBody);

    return new SuccessResponse<CocResponseBody>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Certificate of Coverage is successfully created.",
        certificateOfCoverage);
  }

  @PostMapping("/addListOfCertificate")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<List<CocResponseBody>> processAndCreateListCertificateOfCoverage(
      @RequestBody @Valid List<CocRequestBody> listCocRequestBody) {

    List<CocResponseBody> listCocResponseBody =
        inventoryService.createAndSaveListCertificateOfCoverage(listCocRequestBody);

    return new SuccessResponse<List<CocResponseBody>>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Successfully created a batch of Certificate of Coverage.",
        listCocResponseBody);
  }

  @GetMapping("/getCertificateOfCoverage/{cocNumber}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<CocResponseBody> processAndGetCertificateOfCoverage(
      @PathVariable("cocNumber") String cocNumber) {

    CocResponseBody coc = inventoryService.retrieveCertificateOfCoverageById(cocNumber);

    return new SuccessResponse<CocResponseBody>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully retrieved a Certificate of Coverage.",
        coc);
  }

  @GetMapping("/getAllCertificates")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<List<CocResponseBody>> getAllCertificates() {
    List<CocResponseBody> listOfCoc = inventoryService.retrieveAllCertificates();

    return new SuccessResponse<List<CocResponseBody>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully retrieved all of the Certificates.",
        listOfCoc);
  }
}
