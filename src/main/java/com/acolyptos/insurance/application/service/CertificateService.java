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

@Service
public class CertificateService {

  private final CertificateRepositoryInterface certificateRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;

  public CertificateService(
      CertificateRepositoryInterface certificateRepositoryInterface,
      AgentRepositoryInterface agentRepositoryInterface) {

    this.certificateRepositoryInterface = certificateRepositoryInterface;
    this.agentRepositoryInterface = agentRepositoryInterface;
  }

  public CertificateResponseDto createAndSaveCertificate(
      CertificateRequestDto certificateRequestDto) {

    final UUID id = UUID.fromString(certificateRequestDto.getAgentId());

    if (certificateRepositoryInterface.checkCertificateIfExistsById(
        certificateRequestDto.getCocNumber())) {
      throw new EntityAlreadyExistsException(
          "Certificate of Coverage number: '"
              + certificateRequestDto.getCocNumber()
              + "' already exists. Please check the inventory and COC number.");
    }

    Agent agent =
        agentRepositoryInterface
            .findAgentById(id)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with the ID: '"
                            + id
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    CertificateOfCoverage certificateOfCoverage =
        new CertificateOfCoverage(
            certificateRequestDto.getCocNumber(),
            agent,
            certificateRequestDto.getBatchReference(),
            formatDate(certificateRequestDto.getDateIssued()),
            CertificateStatus.AVAILABLE);

    CertificateOfCoverage savedCertificate =
        certificateRepositoryInterface.saveCertificateOfCoverage(certificateOfCoverage);

    return mapToDto(savedCertificate);
  }

  public List<CertificateResponseDto> createAndSaveAllCertificate(
      List<CertificateRequestDto> listCertificateRequestDtos) {

    List<CertificateOfCoverage> listCertificateOfCoverage = new ArrayList<CertificateOfCoverage>();
    List<CertificateResponseDto> listCertificateResponseDto =
        new ArrayList<CertificateResponseDto>();

    listCertificateRequestDtos.forEach(
        request -> {
          final UUID id = UUID.fromString(request.getAgentId());
          String cocNumber = request.getCocNumber();

          if (certificateRepositoryInterface.checkCertificateIfExistsById(cocNumber)) {
            throw new EntityAlreadyExistsException(
                "Certificate of Coverage number: '"
                    + cocNumber
                    + "' already exists. Please check the inventory and COC number.");
          }

          Agent agent =
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

    List<CertificateOfCoverage> listSavedCertificates =
        certificateRepositoryInterface.saveAllCertificateOfCoverage(listCertificateOfCoverage);

    listSavedCertificates.forEach(
        certificate -> {
          listCertificateResponseDto.add(mapToDto(certificate));
        });

    return listCertificateResponseDto;
  }

  public CertificateResponseDto retrieveCertificateOfCoverageById(String cocNumber) {

    CertificateOfCoverage certificateOfCoverage =
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

  public PaginationResponse<CertificateResponseDto> retrievePaginatedCertificates(
      int pageNumber, int pageSize) {

    List<CertificateResponseDto> listCertificateResponseDto =
        new ArrayList<CertificateResponseDto>();

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<CertificateOfCoverage> paginatedCertificate =
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

  private CertificateResponseDto mapToDto(CertificateOfCoverage certificateOfCoverage) {
    CertificateResponseDto certificateResponseDto = new CertificateResponseDto();
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
