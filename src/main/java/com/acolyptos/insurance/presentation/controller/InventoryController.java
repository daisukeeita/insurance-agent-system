package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InventoryService;
import com.acolyptos.insurance.domain.inventory.CocRequestBody;
import com.acolyptos.insurance.domain.inventory.CocResponseBody;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
}
