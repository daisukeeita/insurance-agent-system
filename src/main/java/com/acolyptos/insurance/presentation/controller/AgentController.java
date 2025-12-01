package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.AgentService;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequestDto;
import com.acolyptos.insurance.domain.agent.AgentResponseDto;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import com.acolyptos.insurance.domain.response.SuccessResponse;
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

/** Controller class for handling incoming HTTP requests related to {@link Agent} operations. */
@Validated
@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

  private final AgentService agentService;

  /**
   * Constructs an {@code AgentController} with required service dependency.
   *
   * @param agentService The service class implementing the business logic for Agent operations.
   */
  public AgentController(final AgentService agentService) {
    this.agentService = agentService;
  }

  /**
   * Creates a new Agent record.
   *
   * <p>Accepts an {@link AgentRegisterRequestDto} from the client, validates it, and sends it to
   * the service layer for persistence.
   *
   * @param agentRegisterRequestDto The DTO containing the details of the agent to be created. It is
   *     bound from the request body using {@code @RequestBody} and validated using {@code @Valid}.
   * @return A {@link SuccessResponse} containing the created {@link AgentResponseDto} and an HTTP
   *     status of {@link HttpStatus#CREATED} (201).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PostMapping("/createAgent")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<AgentResponseDto> createAndSaveAgent(
      @RequestBody @Valid final AgentRegisterRequestDto agentRegisterRequestDto) {

    final AgentResponseDto agent = agentService.createAgent(agentRegisterRequestDto);

    return new SuccessResponse<AgentResponseDto>(
        HttpStatus.CREATED.value(), HttpStatus.CREATED, "Agent created successfully.", agent);
  }

  /**
   * Retrieves a specific {@link Agent} entity based on its username.
   *
   * @param username The username of the agent to retrieve. This value is extracted from the URL
   *     path using {@code @PathVariable}.
   * @return A {@link SuccessResponse} containing the found {@link AgentResponseDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getAgentByUsername/{username}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<AgentResponseDto> findAndRetrieveAgentByUsername(
      @PathVariable("username")
          @NotBlank(
              message = "Agent's username is required to retrieve the agent from the database.")
          final String username) {

    AgentResponseDto agent = agentService.retrieveAgentByUsername(username);

    return new SuccessResponse<AgentResponseDto>(
        HttpStatus.FOUND.value(), HttpStatus.FOUND, "Successfully found the Agent.", agent);
  }

  /**
   * Retrieves a specific {@link Agent} entity based in its license number.
   *
   * @param licenseNumber The license number of the agent to retrieve. This value is extracted from
   *     the URL path using {@code @PathVariable}.
   * @return A {@link SuccessResponse} containing the found {@link AgentResponseDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getAgentByLicenseNumber/{licenseNumber}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<AgentResponseDto> findAndRetrieveAgentByLicenseNumber(
      @PathVariable("licenseNumber")
          @NotBlank(
              message =
                  "Agent's license number is required to retrieve the agent from the databse.")
          final String licenseNumber) {

    AgentResponseDto agent = agentService.retrieveAgentByLicenseNumber(licenseNumber);

    return new SuccessResponse<AgentResponseDto>(
        HttpStatus.FOUND.value(), HttpStatus.FOUND, "Successfully found the Agent.", agent);
  }

  /**
   * Retrieves a specific {@link Agent} entity based in its unique identification number.
   *
   * @param agentId The identification number of the agent to retrieve. This value iis extracted
   *     from the URL path using {@code @PathVariable}.
   * @return A {@link SuccessResponse} containing the found {@link AgentResponseDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getAgentById/{agentId}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<AgentResponseDto> findAndRetrieveAgentById(
      @PathVariable("agentId")
          @NotBlank(
              message = "Agent's ID number is required to retrieve the agent from the database.")
          final String agentId) {

    AgentResponseDto agent = agentService.retrieveAgentById(agentId);

    return new SuccessResponse<AgentResponseDto>(
        HttpStatus.FOUND.value(), HttpStatus.FOUND, "Successfully found the Agent.", agent);
  }

  /**
   * Retrieves a paginated list of all Agents.
   *
   * @param pageNumber The zero-based index of the page to retrieve. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @param pageSize The maximum number of agents to include in the page. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @return A {@link SuccessResponse} containing the found {@link AgentResponseDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally.
   */
  @GetMapping("/getPaginatedAgents")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PaginationResponse<AgentResponseDto>> retrievePaginatedAgents(
      @RequestParam(defaultValue = "0") final int pageNumber,
      @RequestParam(defaultValue = "10") final int pageSize) {

    final PaginationResponse<AgentResponseDto> paginatedAgents =
        agentService.retrievePaginatedAgents(pageNumber, pageSize);

    return new SuccessResponse<PaginationResponse<AgentResponseDto>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found and paginated agents.",
        paginatedAgents);
  }
}
