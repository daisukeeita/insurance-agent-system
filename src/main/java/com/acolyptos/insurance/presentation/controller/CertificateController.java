package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.CertificateService;
import com.acolyptos.insurance.domain.certificate.CertificateRequestDto;
import com.acolyptos.insurance.domain.certificate.CertificateResponseDto;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling incoming HTTP requests related to {@link CertificateOfCoverage}
 * operations.
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class CertificateController {

  private final CertificateService certificateService;

  /**
   * Constructs a {@code CertificateController} with required service dependency.
   *
   * @param certificateService The service class implementing the business logic for Certificate of
   *     Coverage operations.
   */
  public CertificateController(CertificateService certificateService) {
    this.certificateService = certificateService;
  }

  @PostMapping("/addCertificateOfCoverage")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<CertificateResponseDto> createAndSaveCertificateOfCoverage(
      @RequestBody @Valid CertificateRequestDto certificateRequestDto) {

    CertificateResponseDto certificateOfCoverage =
        certificateService.createAndSaveCertificate(certificateRequestDto);

    return new SuccessResponse<CertificateResponseDto>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Certificate of Coverage is successfully created.",
        certificateOfCoverage);
  }
}
