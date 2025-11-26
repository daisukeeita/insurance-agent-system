package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import com.acolyptos.insurance.domain.inventory.CocRepositoryInterface;
import com.acolyptos.insurance.domain.transaction.Policy;
import com.acolyptos.insurance.domain.transaction.PolicyRepositoryInterface;
import com.acolyptos.insurance.domain.transaction.PolicyRequestDto;
import com.acolyptos.insurance.domain.transaction.PolicyResponseDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

  private final PolicyRepositoryInterface policyRepositoryInterface;
  private final CocRepositoryInterface cocRepositoryInterface;
  private final AgentRepositoryInterface agentRepositoryInterface;

  public PolicyService(
      CocRepositoryInterface cocRepositoryInterface,
      AgentRepositoryInterface agentRepositoryInterface,
      PolicyRepositoryInterface policyRepositoryInterface) {
    this.cocRepositoryInterface = cocRepositoryInterface;
    this.agentRepositoryInterface = agentRepositoryInterface;
    this.policyRepositoryInterface = policyRepositoryInterface;
  }

  public PolicyResponseDto processAndSavePolicy(PolicyRequestDto policyRequestDto) {

    CertificateOfCoverage certificateOfCoverage =
        cocRepositoryInterface.getCertificateOfCoverageByNumber(policyRequestDto.getCocNumber());

    Agent agent = agentRepositoryInterface.findAgentByUsername(policyRequestDto.getAgentUsername());

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

    return new PolicyResponseDto(
        savedPolicy.getPolicyId(),
        savedPolicy.getCertificateOfCoverage().getCocNumber(),
        savedPolicy.getAgent().getFullName(),
        savedPolicy.getCustomerName(),
        savedPolicy.getPlateNumber(),
        savedPolicy.getChassisNumber(),
        savedPolicy.getPremiumAmount().toString(),
        savedPolicy.getConvertedSaleTimestamp(),
        savedPolicy.getInsurerBindingTransactionReference());
  }
}
