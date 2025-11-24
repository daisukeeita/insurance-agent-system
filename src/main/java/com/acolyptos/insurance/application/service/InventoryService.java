package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import com.acolyptos.insurance.domain.inventory.CocPageResponse;
import com.acolyptos.insurance.domain.inventory.CocRepositoryInterface;
import com.acolyptos.insurance.domain.inventory.CocRequestBody;
import com.acolyptos.insurance.domain.inventory.CocResponseBody;
import com.acolyptos.insurance.domain.inventory.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final CocRepositoryInterface cocRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;

  public InventoryService(
      CocRepositoryInterface cocRepositoryInterface,
      AgentRepositoryInterface agentRepositoryInterface) {

    this.cocRepositoryInterface = cocRepositoryInterface;
    this.agentRepositoryInterface = agentRepositoryInterface;
  }

  public CocResponseBody createAndSaveCertificateOfCoverage(CocRequestBody cocRequestBody) {

    UUID agentId = UUID.fromString(cocRequestBody.getAgentId());

    Agent agent = agentRepositoryInterface.findAgentById(agentId);

    if (agent == null) {
      throw new EntityDoesNotExistException(
          "Could not find any Agent with id: " + cocRequestBody.getAgentId());
    }

    return filterCocDetails(instantiateAndSaveCertificateOfCoverage(agent, cocRequestBody));
  }

  public List<CocResponseBody> createAndSaveListCertificateOfCoverage(
      List<CocRequestBody> listCocRequestBody) {

    List<CertificateOfCoverage> listCertificateOfCoverage = new ArrayList<>();
    List<CertificateOfCoverage> listSavedCertificates = new ArrayList<>();
    List<CocResponseBody> listCocResponseBody = new ArrayList<>();

    listCocRequestBody.forEach(
        request -> {
          UUID agentId = UUID.fromString(request.getAgentId());

          Agent agent = agentRepositoryInterface.findAgentById(agentId);
          if (agent == null) {
            throw new EntityDoesNotExistException(
                "Could not find any Agent with id: " + "'" + agentId + "'.");
          }

          LocalDate dateIssued = convertToLocalDate(request.getDateIssued());

          CertificateOfCoverage certificateOfCoverage =
              new CertificateOfCoverage(request.getCocNumber());
          certificateOfCoverage.setStatus(Status.AVAILABLE);
          certificateOfCoverage.setProcuredBy(agent);
          certificateOfCoverage.setDateIssued(dateIssued);
          certificateOfCoverage.setBatchReference(request.getBatchReference());

          listCertificateOfCoverage.add(certificateOfCoverage);
        });

    listSavedCertificates.addAll(
        cocRepositoryInterface.saveAllCertificateOfCoverage(listCertificateOfCoverage));

    listSavedCertificates.forEach(
        certificate -> {
          CocResponseBody cocResponseBody = new CocResponseBody(certificate.getCocNumber());
          cocResponseBody.setProcuredBy(certificate.getProcuredBy().getFullName());
          cocResponseBody.setStatus(certificate.getStatus().toString());
          cocResponseBody.setDateIssued(certificate.getDateIssued().toString());
          cocResponseBody.setBatchReference(certificate.getBatchReference());

          listCocResponseBody.add(cocResponseBody);
        });

    return listCocResponseBody;
  }

  public CocResponseBody retrieveCertificateOfCoverageById(String cocNumber) {

    if (cocNumber.trim().isEmpty() || cocNumber == null) {
      throw new InvalidRequestBodyException("COC Number was not provided.");
    }

    CertificateOfCoverage certificateOfCoverage =
        cocRepositoryInterface.getCertificateOfCoverageByNumber(cocNumber);
    if (certificateOfCoverage == null) {
      throw new EntityDoesNotExistException(
          "Cannot find a Certificate of Coverage using: " + "'" + cocNumber + "'.");
    }

    return filterCocDetails(certificateOfCoverage);
  }

  public List<CocResponseBody> retrieveAllCertificates() {
    List<CocResponseBody> listOfCoc = new ArrayList<>();

    cocRepositoryInterface
        .getAllCertificateOfCoverage()
        .forEach(
            certificate -> {
              listOfCoc.add(filterCocDetails(certificate));
            });

    return listOfCoc;
  }

  public CocPageResponse<CocResponseBody> retrieveAndFilterPaginatedCertificates(
      int pageNumber, int pageSize, String sortBy) {

    List<CocResponseBody> listOfCocResponseBody = new ArrayList<>();

    Sort sort = Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<CertificateOfCoverage> paginatedCertificates =
        cocRepositoryInterface.getPaginatedCertificateOfCoverage(pageable);

    paginatedCertificates
        .getContent()
        .forEach(
            certificate -> {
              CocResponseBody responseBody = new CocResponseBody(certificate.getCocNumber());
              responseBody.setBatchReference(certificate.getBatchReference());
              responseBody.setDateIssued(certificate.getDateIssued().toString());
              responseBody.setProcuredBy(certificate.getProcuredBy().getFullName());
              responseBody.setStatus(certificate.getStatus().toString());

              listOfCocResponseBody.add(responseBody);
            });

    return new CocPageResponse<CocResponseBody>(
        listOfCocResponseBody,
        paginatedCertificates.getPageable().getPageNumber(),
        paginatedCertificates.getPageable().getPageSize(),
        paginatedCertificates.getTotalPages(),
        paginatedCertificates.getTotalElements());
  }

  public Page<CertificateOfCoverage> retrievePaginatedCertificates(int pageNumber, int pageSize) {

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return cocRepositoryInterface.getPaginatedCertificateOfCoverage(pageable);
  }

  private LocalDate convertToLocalDate(String localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(localDate, formatter);
  }

  private CertificateOfCoverage instantiateAndSaveCertificateOfCoverage(
      Agent agent, CocRequestBody cocRequestBody) {

    LocalDate dateIssued = convertToLocalDate(cocRequestBody.getDateIssued());

    CertificateOfCoverage certificateOfCoverage =
        new CertificateOfCoverage(cocRequestBody.getCocNumber());
    certificateOfCoverage.setProcuredBy(agent);
    certificateOfCoverage.setBatchReference(cocRequestBody.getBatchReference());
    certificateOfCoverage.setStatus(Status.AVAILABLE);
    certificateOfCoverage.setDateIssued(dateIssued);

    return cocRepositoryInterface.saveCertificateOfCoverage(certificateOfCoverage);
  }

  private CocResponseBody filterCocDetails(CertificateOfCoverage certificateOfCoverage) {

    CocResponseBody cocResponseBody = new CocResponseBody(certificateOfCoverage.getCocNumber());
    cocResponseBody.setProcuredBy(certificateOfCoverage.getProcuredBy().getFullName());
    cocResponseBody.setBatchReference(certificateOfCoverage.getBatchReference());
    cocResponseBody.setDateIssued(certificateOfCoverage.getDateIssued().toString());
    cocResponseBody.setStatus(certificateOfCoverage.getStatus().toString());

    return cocResponseBody;
  }
}
