package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.agent.Agent;
import com.acolyptos.insurance.domain.agent.AgentRegisterRequestDto;
import com.acolyptos.insurance.domain.agent.AgentRepositoryInterface;
import com.acolyptos.insurance.domain.agent.AgentResponseDto;
import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
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

/** Service class for managing {@link Agent} entities. */
@Service
public class AgentService {

  private final AgentRepositoryInterface agentRepositoryInterface;
  private final InsurerRepositoryInterface insurerRepositoryInterface;

  /**
   * Constructs an {@code AgentService} with required repositories.
   *
   * @param agentRepositoryInterface The repository interface for {@link Agent} persistence
   *     operations.
   * @param insurerRepositoryInterface The repository interface for {@link Insurer} persistence
   *     operations.
   */
  public AgentService(
      final AgentRepositoryInterface agentRepositoryInterface,
      final InsurerRepositoryInterface insurerRepositoryInterface) {
    this.agentRepositoryInterface = agentRepositoryInterface;
    this.insurerRepositoryInterface = insurerRepositoryInterface;
  }

  /**
   * Creates a new {@link Agent} in the database based on the provided required DTO.
   *
   * <p>It also checks for existing agent by its username and insurer with its provided name before
   * saving.
   *
   * @param agentRegisterRequestDto The DTO containing the required details of the agent to create.
   * @return The {@link AgentResponseDto} of newly created {@link Agent}, including its generated
   *     ID.
   * @throws EntityAlreadyExistsException if an agent with the same username already exists.
   * @throws EntityDoesNotExistException if an insurer with the provided name does not exists.
   */
  public AgentResponseDto createAgent(final AgentRegisterRequestDto agentRegisterRequestDto) {
    if (agentRepositoryInterface.checkAgentIfExistsByUsername(
        agentRegisterRequestDto.getUsername())) {
      throw new EntityAlreadyExistsException(
          "Agent's username: '"
              + agentRegisterRequestDto.getUsername()
              + "' already used. Please try a different username.");
    }

    final Insurer insurer =
        insurerRepositoryInterface
            .getInsurerByInsurerName(agentRegisterRequestDto.getInsurer())
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Insurer with the name: '"
                            + agentRegisterRequestDto.getInsurer()
                            + "' doest not exist in the database. Please make sure it was provided"
                            + " correctly."));

    // TODO: IMPLEMENT HASH PASSWORD BEFORE SAVING THE AGENT TO THE DATABASE.
    final Agent agent =
        new Agent(
            insurer,
            agentRegisterRequestDto.getUsername().trim(),
            agentRegisterRequestDto.getPassword(),
            agentRegisterRequestDto.getFullName().trim(),
            agentRegisterRequestDto.getLicenseNumber().trim(),
            formatDate(agentRegisterRequestDto.getDateHired().trim()));

    final Agent savedAgent = agentRepositoryInterface.saveAgent(agent);

    return mapToResponseDto(savedAgent);
  }

  /**
   * Retrieves an agent from the database using its username.
   *
   * @param username The username of the agent to retrieve.
   * @return The {@link AgentResponseDto} of the retrieved agent.
   * @throws EntityDoesNotExistException if no agent with the given username is found.
   */
  public AgentResponseDto retrieveAgentByUsername(final String username) {

    if (username == null || username.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Agent's username is required to retrieve the agent from the database.");
    }

    final Agent agent =
        agentRepositoryInterface
            .findAgentByUsername(username)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with username: '"
                            + username
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    return mapToResponseDto(agent);
  }

  /**
   * Retrieves an agent from the database using its license number.
   *
   * @param licenseNumber The license number of the agent to retrieve.
   * @return The {@link AgentResponseDto} of the retrieved agent.
   * @throws EntityDoesNotExistException if no agent with the given license number is found.
   */
  public AgentResponseDto retrieveAgentByLicenseNumber(final String licenseNumber) {

    if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Agent's license number is required to retrieve the agent from the databse.");
    }

    final Agent agent =
        agentRepositoryInterface
            .findAgentByLicenseNumber(licenseNumber)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with licenseNumber: '"
                            + licenseNumber
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    return mapToResponseDto(agent);
  }

  /**
   * Retrieves an agent from the database using its unique identification number.
   *
   * @param agentId The unique identification number of the agent to retrieve.
   * @return The {@link AgentResponseDto} of the retrived agent.
   * @throws EntityDoesNotExistException if no agent with the given identification number is found.
   */
  public AgentResponseDto retrieveAgentById(final String agentId) {

    if (agentId == null || agentId.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Agent's ID number is required to retrieve the agent from the database.");
    }

    final UUID id = UUID.fromString(agentId);

    final Agent agent =
        agentRepositoryInterface
            .findAgentById(id)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "Agent with ID number: '"
                            + id
                            + "' does not exist in the database. Please make sure it was provided"
                            + " correctly."));

    return mapToResponseDto(agent);
  }

  /**
   * Retrieves a paginated list of all Agents.
   *
   * @param pageNumber The zero-based index of the page to retrieve.
   * @param pageSize The maximum number of agents to include in the page.
   * @return The {@link PaginationResponse} DTO containing the list of {@link AgentResponseDto} for
   *     the requested page and pagination metadata.
   */
  public PaginationResponse<AgentResponseDto> retrievePaginatedAgents(
      final int pageNumber, final int pageSize) {

    final List<AgentResponseDto> listAgentResponseDto = new ArrayList<AgentResponseDto>();

    final Pageable pageable = PageRequest.of(pageNumber, pageSize);
    final Page<Agent> paginatedAgents = agentRepositoryInterface.getPaginatedAgents(pageable);

    paginatedAgents
        .getContent()
        .forEach(
            agent -> {
              final AgentResponseDto agentResponseDto = mapToResponseDto(agent);

              listAgentResponseDto.add(agentResponseDto);
            });

    return new PaginationResponse<AgentResponseDto>(
        listAgentResponseDto,
        paginatedAgents.getPageable().getPageNumber(),
        paginatedAgents.getPageable().getPageSize(),
        paginatedAgents.getTotalPages(),
        paginatedAgents.getTotalElements());
  }

  private AgentResponseDto mapToResponseDto(final Agent agent) {
    final AgentResponseDto agentResponseDto = new AgentResponseDto();
    agentResponseDto.setAgentId(agent.getAgentId().toString());
    agentResponseDto.setUsername(agent.getUsername());
    agentResponseDto.setFullName(agent.getFullName());
    agentResponseDto.setLicenseNumber(agent.getLicenseNumber());
    agentResponseDto.setInsurerName(agent.getInsurer().getInsurerName());

    return agentResponseDto;
  }

  private LocalDate formatDate(final String dateHired) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(dateHired, formatter);
  }
}
