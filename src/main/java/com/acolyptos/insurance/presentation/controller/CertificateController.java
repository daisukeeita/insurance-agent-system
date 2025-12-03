package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.CertificateService;
import com.acolyptos.insurance.domain.certificate.CertificateRequestDto;
import com.acolyptos.insurance.domain.certificate.CertificateResponseDto;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling incoming HTTP requests related to {@link CertificateOfCoverage}
 * operations.
 */
@Validated
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

  @PostMapping("/addListCertificateOfCoverage")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<List<CertificateResponseDto>> createAndSaveListCertificateOfCoverage(
      @RequestBody @Valid List<CertificateRequestDto> listCertificateRequestDto) {

    List<CertificateResponseDto> listCertificateResponseDto =
        certificateService.createAndSaveAllCertificate(listCertificateRequestDto);

    return new SuccessResponse<List<CertificateResponseDto>>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "List of certificates created successfully.",
        listCertificateResponseDto);
  }

  @GetMapping("/getCertificateById/{cocNumber}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<CertificateResponseDto> findAndRetrieveCertificateById(
      @PathVariable("cocNumber")
          @NotBlank(
              message =
                  "Certificate's COC Number is required to retrieve the certificate from the"
                      + " database.")
          final String cocNumber) {

    CertificateResponseDto certificateResponseDto =
        certificateService.retrieveCertificateOfCoverageById(cocNumber);

    return new SuccessResponse<CertificateResponseDto>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found the Certificate.",
        certificateResponseDto);
  }

  @GetMapping("/getPaginatedCertificates")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PaginationResponse<CertificateResponseDto>> retrievePaginatedCertificates(
      @RequestParam(defaultValue = "0") final int pageNumber,
      @RequestParam(defaultValue = "10") final int pageSize) {
    PaginationResponse<CertificateResponseDto> paginatedCertificates =
        certificateService.retrievePaginatedCertificates(pageNumber, pageSize);

    return new SuccessResponse<PaginationResponse<CertificateResponseDto>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found and paginated certificates.",
        paginatedCertificates);
  }
}
