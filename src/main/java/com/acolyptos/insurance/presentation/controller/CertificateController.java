package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.CertificateService;
import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
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
import org.springframework.web.bind.annotation.PutMapping;
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
  public CertificateController(final CertificateService certificateService) {
    this.certificateService = certificateService;
  }

  /**
   * Creates a new Certificate of Coverage record.
   *
   * <p>Accepts a {@link CertificateRequestDto} from the client, validates it, and send it to the
   * service layer for persistence.
   *
   * @param certificateRequestDto The DTO containing the details of the certificate to be created.
   *     It is bound from the request body using {@code @RequestBody} and validated using
   *     {@code @valid}.
   * @return A {@link SuccessResponse} containing the created {@link CertificateResponseDto} and a
   *     HTTP status of {@link HttpStatus#CREATED} (201).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PostMapping("/addCertificateOfCoverage")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<CertificateResponseDto> createAndSaveCertificateOfCoverage(
      @RequestBody @Valid final CertificateRequestDto certificateRequestDto) {

    final CertificateResponseDto certificateOfCoverage =
        certificateService.createAndSaveCertificate(certificateRequestDto);

    return new SuccessResponse<CertificateResponseDto>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Certificate of Coverage is successfully created.",
        certificateOfCoverage);
  }

  /**
   * Creates a list of new Certificate of Coverage record.
   *
   * <p>Accepts a list of {@link CertificateRequestDto} from the client, validates it, and send it
   * to the service layer for persistence.
   *
   * @param listCertificateRequestDto The {@link List} containing {@link CertificateRequestDto} to
   *     be created.
   * @return A {@link SuccessResponse} containing the list of created {@link CertificateResponseDto}
   *     and a HTTP status of {@link HttpStatus#CREATED} (201).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PostMapping("/addListCertificateOfCoverage")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<List<CertificateResponseDto>> createAndSaveListCertificateOfCoverage(
      @RequestBody @Valid final List<CertificateRequestDto> listCertificateRequestDto) {

    final List<CertificateResponseDto> listCertificateResponseDto =
        certificateService.createAndSaveAllCertificate(listCertificateRequestDto);

    return new SuccessResponse<List<CertificateResponseDto>>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "List of certificates created successfully.",
        listCertificateResponseDto);
  }

  /**
   * Updates the status of a specific {@link CertificateOfCoverage} entity based on it COC Number.
   *
   * @param cocNumber The COC Number or unique identification number to update. This value is
   *     extracted from the URL path using {@code @PathVariable}.
   * @param status The new status to update in existing certificate entity.It is bound from the
   *     request body using {@code @RequestBody} and validated using {@code @Validated} and
   *     {@code @NotBlank}.
   * @return A {@link SuccessResponse} containing the updated {@link CertificateResponseDto} and a
   *     HTTP status of {@link HttpStatus#OK} (200).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PutMapping("updateCertificateById/{cocNumber}")
  @ResponseStatus(HttpStatus.OK)
  public SuccessResponse<CertificateResponseDto> retrieveAndUpdateCertificateStatus(
      @PathVariable
          @NotBlank(
              message =
                  "Certificate's COC Number is required to update the certificate from the"
                      + " database.")
          final String cocNumber,
      @RequestBody
          @NotBlank(message = "Certificate's status is required to update its current status.")
          final String status) {

    final CertificateResponseDto certificateResponseDto =
        certificateService.retrieveAndUpdateCertificateStatus(cocNumber, status);

    return new SuccessResponse<CertificateResponseDto>(
        HttpStatus.OK.value(),
        HttpStatus.OK,
        "Certificate successfully updated.",
        certificateResponseDto);
  }

  /**
   * Retrieves a specific {@link CertificateOfCoverage} entity based on its COC Number.
   *
   * @param cocNumber The COC Number or unique identification number of the certificate to retrieve.
   *     This value is extracted from the URL path using {@code @PathVariable}.
   * @return A {@link SuccessResponse} containing the found {@link CertificateResponseDto} and a
   *     HTTP status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exception handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getCertificateById/{cocNumber}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<CertificateResponseDto> findAndRetrieveCertificateById(
      @PathVariable("cocNumber")
          @NotBlank(
              message =
                  "Certificate's COC Number is required to retrieve the certificate from the"
                      + " database.")
          final String cocNumber) {

    final CertificateResponseDto certificateResponseDto =
        certificateService.retrieveCertificateOfCoverageById(cocNumber);

    return new SuccessResponse<CertificateResponseDto>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found the Certificate.",
        certificateResponseDto);
  }

  /**
   * Retrieves a paginated list of all Certificates.
   *
   * @param pageNumber The zero-based index of the page to retrieve. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @param pageSize The maximum number of certificates to include in the page. Extracted from URL
   *     query parameters using {@code @RequestParam}.
   * @return A {@link SuccessResponse} containing the found {@link CertificateResponseDto} and a
   *     HTTP status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally.
   */
  @GetMapping("/getPaginatedCertificates")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PaginationResponse<CertificateResponseDto>> retrievePaginatedCertificates(
      @RequestParam(defaultValue = "0") final int pageNumber,
      @RequestParam(defaultValue = "10") final int pageSize) {
    final PaginationResponse<CertificateResponseDto> paginatedCertificates =
        certificateService.retrievePaginatedCertificates(pageNumber, pageSize);

    return new SuccessResponse<PaginationResponse<CertificateResponseDto>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found and paginated certificates.",
        paginatedCertificates);
  }
}
