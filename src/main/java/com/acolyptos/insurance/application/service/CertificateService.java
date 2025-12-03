package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
import com.acolyptos.insurance.domain.certificate.CertificateRepositoryInterface;
import com.acolyptos.insurance.domain.certificate.CertificateRequestDto;
import com.acolyptos.insurance.domain.certificate.CertificateResponseDto;
import com.acolyptos.insurance.domain.certificate.CertificateStatus;
import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Service class for managing {@link CertificateOfCoverage} entities. */
@Service
public class CertificateService {

  private final CertificateRepositoryInterface certificateRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;

  /**
   * Constructs a {@link CertificateService} with required respositories.
   *
   * @param certificateRepositoryInterface The repository interface for {@link
   *     CertificateOfCoverage} persistence. operations.
   * @param agentRepositoryInterface The repository interface for {@link Agent} persistence
   *     operations.
   */
  public CertificateService(
      final CertificateRepositoryInterface certificateRepositoryInterface,
      final AgentRepositoryInterface agentRepositoryInterface) {

    this.certificateRepositoryInterface = certificateRepositoryInterface;
    this.agentRepositoryInterface = agentRepositoryInterface;
  }

  /**
   * Creates a new {@link CertificateOfCoverage} in the database based on the provided request DTO.
   *
   * <p>It also checks for an existing certificate with the same COC Number and agent with its
   * unique identification number before saving.
   *
   * @param certificateRequestDto The DTO containing the details for the certificate to create.
   * @return The {@link CertificateResponseDto} of newly created {@link CertificateOfCoverage},
   *     including its COC Number.
   * @throws EntityAlreadyExistsException if a certificate with the same COC Number already exists
   *     in the database.
   * @throws EntityDoesNotExistException if an agent with the provided unique identification does
   *     not exists in the database.
   */
  public CertificateResponseDto createAndSaveCertificate(
      final CertificateRequestDto certificateRequestDto) {

    final UUID id = UUID.fromString(certificateRequestDto.getAgentId());

    if (certificateRepositoryInterface.checkCertificateIfExistsById(
        certificateRequestDto.getCocNumber())) {
      throw new EntityAlreadyExistsException(
          "Certificate of Coverage number: '"
              + certificateRequestDto.getCocNumber()
              + "' already exists. Please check the inventory and COC number.");
    }

    final Agent agent =
        agentRepositoryInterface
            .findAgentById(id)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with the ID: '"
                            + id
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    final CertificateOfCoverage certificateOfCoverage =
        new CertificateOfCoverage(
            certificateRequestDto.getCocNumber(),
            agent,
            certificateRequestDto.getBatchReference(),
            formatDate(certificateRequestDto.getDateIssued()),
            CertificateStatus.AVAILABLE);

    final CertificateOfCoverage savedCertificate =
        certificateRepositoryInterface.saveCertificateOfCoverage(certificateOfCoverage);

    return mapToDto(savedCertificate);
  }

  /**
   * Creates a {@link List} of {@link CertificateOfCoverage} in the database based on the provided
   * list of request DTO.
   *
   * <p>It also checks for an existing certificate with the same COC Number and agent with its
   * unique identification number per element of the list before saving.
   *
   * @param listCertificateRequestDto The list of DTOs containing the details of the certificate,
   *     per element, to create.
   * @return A {@link List} containing the {@link CertificateResponseDto} of newly created {@link
   *     CertificateOfCoverage}, including its COC Number per element.
   * @throws EntityAlreadyExistsException if a certificate with the same COC Number already exists
   *     in the database.
   * @throws EntityDoesNotExistException if an agent with the provided unique identification does
   *     not exists in the database.
   */
  public List<CertificateResponseDto> createAndSaveAllCertificate(
      final List<CertificateRequestDto> listCertificateRequestDto) {

    final List<CertificateOfCoverage> listCertificateOfCoverage =
        new ArrayList<CertificateOfCoverage>();
    final List<CertificateResponseDto> listCertificateResponseDto =
        new ArrayList<CertificateResponseDto>();

    listCertificateRequestDto.forEach(
        request -> {
          final UUID id = UUID.fromString(request.getAgentId());
          final String cocNumber = request.getCocNumber();

          if (certificateRepositoryInterface.checkCertificateIfExistsById(cocNumber)) {
            throw new EntityAlreadyExistsException(
                "Certificate of Coverage number: '"
                    + cocNumber
                    + "' already exists. Please check the inventory and COC number.");
          }

          final Agent agent =
              agentRepositoryInterface
                  .findAgentById(id)
                  .orElseThrow(
                      () ->
                          new EntityDoesNotExistException(
                              "Agent with the ID: '"
                                  + id
                                  + "' does not exist in the database. Please make sure it was"
                                  + " provided correctly."));

          listCertificateOfCoverage.add(
              new CertificateOfCoverage(
                  cocNumber,
                  agent,
                  request.getBatchReference(),
                  formatDate(request.getDateIssued()),
                  CertificateStatus.AVAILABLE));
        });

    final List<CertificateOfCoverage> listSavedCertificates =
        certificateRepositoryInterface.saveAllCertificateOfCoverage(listCertificateOfCoverage);

    listSavedCertificates.forEach(
        certificate -> {
          listCertificateResponseDto.add(mapToDto(certificate));
        });

    return listCertificateResponseDto;
  }

  /**
   * Updates an existing certificate entity based on the provided DTO.
   *
   * @param cocNumber The COC Number or its unique identification number of the certificate to
   *     update.
   * @param status The new status to update in existing certificate entity.
   * @return The {@link CertificateResponseDto} of newly updated {@link CertificateOfCoverage},
   *     including its COC Number.
   * @throws InvalidRequestBodyException if the given string is not matched to the values of {@link
   *     CertificateStatus}.
   * @throws EntityDoesNotExistException if a certificate with the provided unique identification
   *     does not exists in the database.
   */
  public CertificateResponseDto retrieveAndUpdateCertificateStatus(
      final String cocNumber, final String status) {

    final CertificateStatus certificateStatus =
        CertificateStatus.fromString(status)
            .orElseThrow(
                () ->
                    new InvalidRequestBodyException(
                        "The given status: '"
                            + status
                            + "' is invalid. Valid status are: "
                            + CertificateStatus.values()));

    final CertificateOfCoverage certificateOfCoverage =
        certificateRepositoryInterface
            .getCertificateOfCoverageById(cocNumber)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Certificate of Coverage with ID: '"
                            + cocNumber
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    certificateOfCoverage.setStatus(certificateStatus);

    final CertificateOfCoverage updatedCertificate =
        certificateRepositoryInterface.saveCertificateOfCoverage(certificateOfCoverage);

    return mapToDto(updatedCertificate);
  }

  /**
   * Retrieves a Certificate of Coverage from the database using its COC Number.
   *
   * @param cocNumber The COC Number or its unique identification number of the certificate to
   *     retrieve.
   * @return The {@link CertificateResponseDto} of the retrieved certificate.
   * @throws EntityDoesNotExistException if no certificate with the given COC Number is found.
   */
  public CertificateResponseDto retrieveCertificateOfCoverageById(final String cocNumber) {

    final CertificateOfCoverage certificateOfCoverage =
        certificateRepositoryInterface
            .getCertificateOfCoverageById(cocNumber)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Certificate of Coverage with ID: '"
                            + cocNumber
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    return mapToDto(certificateOfCoverage);
  }

  /**
   * Retrieves a paginated list of all Certificate of Coverage.
   *
   * @param pageNumber The zero-based index of the page to retrieve.
   * @param pageSize The maximum number of certificates to include in the page.
   * @return The {@link PaginationResponse} DTO containing the list of {@link
   *     CertificateResponseDto} for the requested page and pagination metadata.
   */
  public PaginationResponse<CertificateResponseDto> retrievePaginatedCertificates(
      final int pageNumber, final int pageSize) {

    final List<CertificateResponseDto> listCertificateResponseDto =
        new ArrayList<CertificateResponseDto>();

    final Pageable pageable = PageRequest.of(pageNumber, pageSize);
    final Page<CertificateOfCoverage> paginatedCertificate =
        certificateRepositoryInterface.getPaginatedCertificateOfCoverage(pageable);

    paginatedCertificate.forEach(
        certificate -> {
          listCertificateResponseDto.add(mapToDto(certificate));
        });

    return new PaginationResponse<CertificateResponseDto>(
        listCertificateResponseDto,
        paginatedCertificate.getPageable().getPageNumber(),
        paginatedCertificate.getPageable().getPageSize(),
        paginatedCertificate.getTotalPages(),
        paginatedCertificate.getTotalElements());
  }

  private CertificateResponseDto mapToDto(final CertificateOfCoverage certificateOfCoverage) {
    final CertificateResponseDto certificateResponseDto = new CertificateResponseDto();
    certificateResponseDto.setCocNumber(certificateOfCoverage.getCocNumber());
    certificateResponseDto.setProcuredBy(certificateOfCoverage.getProcuredBy().getFullName());
    certificateResponseDto.setBatchReference(certificateOfCoverage.getBatchReference());
    certificateResponseDto.setDateIssued(certificateOfCoverage.getDateIssued().toString());
    certificateResponseDto.setStatus(certificateOfCoverage.getStatus().toString());

    return certificateResponseDto;
  }

  private LocalDate formatDate(final String dateHired) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(dateHired, formatter);
  }
}
