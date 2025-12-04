package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
import com.acolyptos.insurance.domain.certificate.CertificateRepositoryInterface;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import com.acolyptos.insurance.domain.transaction.Policy;
import com.acolyptos.insurance.domain.transaction.PolicyRepositoryInterface;
import com.acolyptos.insurance.domain.transaction.PolicyRequestDto;
import com.acolyptos.insurance.domain.transaction.PolicyResponseDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

  private final PolicyRepositoryInterface policyRepositoryInterface;
  private final CertificateRepositoryInterface certificateRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;

  public PolicyService(
      CertificateRepositoryInterface certificateRepositoryInterface,
      AgentRepositoryInterface agentRepositoryInterface,
      PolicyRepositoryInterface policyRepositoryInterface) {
    this.certificateRepositoryInterface = certificateRepositoryInterface;
    this.agentRepositoryInterface = agentRepositoryInterface;
    this.policyRepositoryInterface = policyRepositoryInterface;
  }

  public PolicyResponseDto createPolicy(PolicyRequestDto policyRequestDto) {

    String cocNumber = policyRequestDto.getCocNumber();
    String agentUsername = policyRequestDto.getAgentUsername();

    CertificateOfCoverage certificateOfCoverage =
        certificateRepositoryInterface
            .getCertificateOfCoverageById(cocNumber)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Certificate with COC Number: '"
                            + cocNumber
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    Agent agent =
        agentRepositoryInterface
            .findAgentByUsername(agentUsername)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with the username: '"
                            + agentUsername
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Policy policy =
        new Policy(
            policyRequestDto.getPolicyId(),
            certificateOfCoverage,
            agent,
            policyRequestDto.getCustomerFullName(),
            policyRequestDto.getPlateNumber(),
            policyRequestDto.getChassisNumber(),
            new BigDecimal(policyRequestDto.getPremiumAmount()),
            LocalDateTime.parse(policyRequestDto.getSaleTimestamp(), formatter),
            policyRequestDto.getInsurerBindingTransactionReference());

    Policy savedPolicy = policyRepositoryInterface.savePolicy(policy);

    return mapToResponseDTO(savedPolicy);
  }

  public PolicyResponseDto retrievePolicyById(String policyId) {

    Policy policy =
        policyRepositoryInterface
            .findPolicyById(policyId)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Policy with ID: '"
                            + policyId
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    return mapToResponseDTO(policy);
  }

  public PaginationResponse<PolicyResponseDto> retrievePaginatedPolicy(
      int pageNumber, int pageSize) {

    List<PolicyResponseDto> listPolicyResponseDto = new ArrayList<PolicyResponseDto>();

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Policy> paginatedPolicies = policyRepositoryInterface.getPaginatedPolicy(pageable);

    paginatedPolicies
        .getContent()
        .forEach(
            policy -> {
              PolicyResponseDto policyResponseDto = mapToResponseDTO(policy);

              listPolicyResponseDto.add(policyResponseDto);
            });

    return new PaginationResponse<>(
        listPolicyResponseDto,
        paginatedPolicies.getPageable().getPageNumber(),
        paginatedPolicies.getPageable().getPageSize(),
        paginatedPolicies.getTotalPages(),
        paginatedPolicies.getTotalElements());
  }

  private PolicyResponseDto mapToResponseDTO(Policy policy) {

    PolicyResponseDto policyResponseDto = new PolicyResponseDto();
    policyResponseDto.setPolicyId(policy.getPolicyId());
    policyResponseDto.setCocNumber(policy.getCertificateOfCoverage().getCocNumber());
    policyResponseDto.setAgentName(policy.getAgent().getFullName());
    policyResponseDto.setCustomerName(policy.getCustomerName());
    policyResponseDto.setPlateNumber(policy.getPlateNumber());
    policyResponseDto.setChassisNumber(policy.getChassisNumber());
    policyResponseDto.setPremiumAmount(policy.getPremiumAmount().toString());
    policyResponseDto.setSaleTimestamp(policy.getConvertedSaleTimestamp());
    policyResponseDto.setInsurerBindingTransactionReference(
        policy.getInsurerBindingTransactionReference());

    return policyResponseDto;
  }
}
