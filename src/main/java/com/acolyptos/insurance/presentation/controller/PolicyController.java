package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.PolicyService;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import com.acolyptos.insurance.domain.transaction.PolicyRequestDto;
import com.acolyptos.insurance.domain.transaction.PolicyResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

/** Controller class for handling incoming HTTP requests related to {@link Policy} operations. */
@Validated
@RestController
@RequestMapping("api/v1/policy")
public class PolicyController {

  private final PolicyService policyService;

  /**
   * Constructs a {@code PolicyController} with required service dependency.
   *
   * @param policyService The service class implementing the business logic from {@link Policy}
   *     operations.
   */
  public PolicyController(PolicyService policyService) {
    this.policyService = policyService;
  }

  /**
   * Creates a new Policy record.
   *
   * <p>Accepts a {@link PolicyRequestDto} from the client, validates it, and sends it to the
   * service layer for persistence.
   *
   * @param policyRequestDto The DTO containing the details of the policy to be created. It is bound
   *     from the request body using {@code @RequestBody} and validated using {@code Valid}.
   * @return A {@link SuccessResponse} containing the created {@link PolicyResponseDto} and an HTTP
   *     status or {@link HttpStatus#CREATED} (201).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PostMapping("/createPolicy")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<PolicyResponseDto> acceptAndSavePolicy(
      @RequestBody @Valid PolicyRequestDto policyRequestDto) {
    PolicyResponseDto responseDto = policyService.createPolicy(policyRequestDto);

    return new SuccessResponse<PolicyResponseDto>(
        HttpStatus.CREATED.value(),
        HttpStatus.CREATED,
        "Policy with " + "'" + policyRequestDto.getPolicyId() + "'" + " created successfully.",
        responseDto);
  }

  /**
   * Retrieves a specific {@link Policy} entity based in its unique identification number.
   *
   * @param policyId The identification number of the Policy to retrieve. This value is extracted
   *     from the URL path using {@code @PathVariable}.
   * @return A {@link SuccessResponse} containing the found {@link PolicyResponseDto} and an HTTP
   *     status os {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getPolicyById/{policyId}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PolicyResponseDto> findAndRetrievePolicyById(
      @PathVariable("policyId")
          @NotBlank(
              message = "Policy ID number is required to retrieve the Policy from the database.")
          final String policyId) {

    PolicyResponseDto policyResponseDto = policyService.retrievePolicyById(policyId);

    return new SuccessResponse<PolicyResponseDto>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found the Policy",
        policyResponseDto);
  }

  /**
   * Retrieves a paginated list of all Policy.
   *
   * @param pageNumber The zero-based index of the page to retrieve. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @param pageSize The maximum number of policy to include in the page. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @return A {@link SuccessResponse} containing the found {@link PolicyResponseDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally.
   */
  @GetMapping("/getPaginatedPolicy")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PaginationResponse<PolicyResponseDto>> retrievePaginatedPolicy(
      @RequestParam(defaultValue = "0") final int pageNumber,
      @RequestParam(defaultValue = "10") final int pageSize) {

    PaginationResponse<PolicyResponseDto> paginatedPolicy =
        policyService.retrievePaginatedPolicy(pageNumber, pageSize);

    return new SuccessResponse<PaginationResponse<PolicyResponseDto>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found and paginated policy.",
        paginatedPolicy);
  }
}
