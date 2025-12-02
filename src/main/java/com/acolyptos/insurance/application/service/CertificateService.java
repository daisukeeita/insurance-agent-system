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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
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

    CertificateResponseDto certificateResponseDto = new CertificateResponseDto();
    certificateResponseDto.setCocNumber(savedCertificate.getCocNumber());
    certificateResponseDto.setProcuredBy(savedCertificate.getProcuredBy().getFullName());
    certificateResponseDto.setBatchReference(savedCertificate.getBatchReference());
    certificateResponseDto.setDateIssued(savedCertificate.getDateIssued().toString());
    certificateResponseDto.setStatus(savedCertificate.getStatus().toString());

    return certificateResponseDto;
  }

  private LocalDate formatDate(final String dateHired) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(dateHired, formatter);
  }
}
