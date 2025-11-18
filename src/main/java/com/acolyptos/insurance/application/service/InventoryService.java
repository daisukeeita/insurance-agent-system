package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import com.acolyptos.insurance.domain.inventory.CocRepositoryInterface;
import com.acolyptos.insurance.domain.inventory.CocRequestBody;
import com.acolyptos.insurance.domain.inventory.CocResponseBody;
import com.acolyptos.insurance.domain.inventory.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
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
