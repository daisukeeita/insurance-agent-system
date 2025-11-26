package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.PolicyService;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import com.acolyptos.insurance.domain.transaction.PolicyRequestDto;
import com.acolyptos.insurance.domain.transaction.PolicyResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/policy")
public class PolicyController {

  private final PolicyService policyService;

  public PolicyController(PolicyService policyService) {
    this.policyService = policyService;
  }

  @PostMapping("/createPolicy")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<PolicyResponseDto> acceptAndSavePolicy(
      @RequestBody @Valid PolicyRequestDto policyRequestDto) {
    PolicyResponseDto responseDto = policyService.processAndSavePolicy(policyRequestDto);

    return new SuccessResponse<PolicyResponseDto>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Policy with " + "'" + policyRequestDto.getPolicyId() + "'" + " created successfully.",
        responseDto);
  }
}
