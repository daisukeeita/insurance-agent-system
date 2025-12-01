package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import com.acolyptos.insurance.domain.insurer.InsurerRequestDto;
import com.acolyptos.insurance.domain.insurer.InsurerResponseDto;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Service class for managing {@link Insurer} entities. */
@Service
public class InsurerService {

  private final InsurerRepositoryInterface insurerRepositoryInterface;

  /**
   * Constructs an {@code InsurerService} with required repository.
   *
   * @param insurerRepositoryInterface The repository interface for {@link Insurer} persistence
   *     operations.
   */
  public InsurerService(InsurerRepositoryInterface insurerRepositoryInterface) {
    this.insurerRepositoryInterface = insurerRepositoryInterface;
  }

  /**
   * Creates a new {@link Insurer} in the database based on the provided request DTO.
   *
   * <p>It also checks for an existing insurer with the same name before saving.
   *
   * @param insurerRequestDto The DTO containing the name and address of the insurer to create.
   * @return The {@link InsurerResponseDto} of newly created {@link Insurer}, including its
   *     generated ID.
   * @throws EntityAlreadyExistsException if an insurer with the same name already exists.
   */
  public InsurerResponseDto createInsurer(InsurerRequestDto insurerRequestDto) {

    String insurerName = insurerRequestDto.getInsurerName().trim();

    boolean insurerDoesExists =
        insurerRepositoryInterface.checkInsurerIfExistsByInsurerName(insurerName);

    if (insurerDoesExists) {
      throw new EntityAlreadyExistsException(
          "The Insurer: '" + insurerName + "' already exists in the database.'");
    }

    Insurer insurer =
        new Insurer(insurerRequestDto.getInsurerName(), insurerRequestDto.getInsurerAddress());

    Insurer savedInsurer = insurerRepositoryInterface.saveInsurer(insurer);

    InsurerResponseDto insurerResponseDto = new InsurerResponseDto();
    insurerResponseDto.setInsurerId(savedInsurer.getInsurerId().toString());
    insurerResponseDto.setInsurerName(savedInsurer.getInsurerName());
    insurerResponseDto.setInsurerAddress(savedInsurer.getInsurerAddress());

    return insurerResponseDto;
  }

  /**
   * Retrieves an insurer from the database using its name.
   *
   * @param insurerName The name of the insurer to retrieve.
   * @return The {@link InsurerResponseDto} of the retrieved insurer.
   * @throws InvalidRequestBodyException if the insurerName is null or empty/blank.
   * @throws EntityDoesNotExistException if no insurer with the given name is found.
   */
  public InsurerResponseDto retrieveInsurerByName(String insurerName) {

    if (insurerName == null || insurerName.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Insurer Name is required to find and retrieve the data.");
    }

    Insurer insurer =
        insurerRepositoryInterface
            .getInsurerByInsurerName(insurerName)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "The insurer name: '" + insurerName + "' does not exist in the database."));

    InsurerResponseDto insurerResponseDto = new InsurerResponseDto();
    insurerResponseDto.setInsurerId(insurer.getInsurerId().toString());
    insurerResponseDto.setInsurerName(insurer.getInsurerName());
    insurerResponseDto.setInsurerAddress(insurer.getInsurerAddress());

    return insurerResponseDto;
  }

  /**
   * Retrieves an Insurer from the database using its unique identifier (ID).
   *
   * @param insurerId The UUID (as a String) of the insurer to retrieve.
   * @return The {@link InsurerResponseDto} of the retrieved insurer.
   * @throws InvalidRequestBodyException if the insurerId is null or empty/blank.
   * @throws EntityDoesNotExistException if no insurer with given ID is found.
   * @throws IllegalArgumentException if the insurerId strinig is now a valid UUID format.
   */
  public InsurerResponseDto retrieveInsurerById(String insurerId) {

    if (insurerId == null || insurerId.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Insurer ID is required to find and retrieve the data.");
    }

    UUID id = UUID.fromString(insurerId);

    Insurer insurer =
        insurerRepositoryInterface
            .getInsurerById(id)
            .orElseThrow(
                () ->
                    new EntityDoesNotExistException(
                        "The insurer ID: '" + id + "' does not exist in the database."));

    InsurerResponseDto insurerResponseDto = new InsurerResponseDto();
    insurerResponseDto.setInsurerId(insurer.getInsurerId().toString());
    insurerResponseDto.setInsurerName(insurer.getInsurerName());
    insurerResponseDto.setInsurerAddress(insurer.getInsurerAddress());

    return insurerResponseDto;
  }

  /**
   * Retrieves a paginated list of all Insurers.
   *
   * @param pageNumber The zero-based index of the page to retrieve.
   * @param pageSize The maximum number of insurers to include in the page.
   * @return A {@link PaginationResponse} DTO containing the list of {@link InsurerResponseDto} for
   *     the requested page and pagination metadata.
   */
  public PaginationResponse<InsurerResponseDto> retrievePaginatedInsurers(
      int pageNumber, int pageSize) {

    List<InsurerResponseDto> listInsurerResponseDto = new ArrayList<>();

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Insurer> paginatedInsurer = insurerRepositoryInterface.getPaginatedInsurers(pageable);

    paginatedInsurer
        .getContent()
        .forEach(
            insurer -> {
              InsurerResponseDto insurerResponseDto = new InsurerResponseDto();
              insurerResponseDto.setInsurerId(insurer.getInsurerId().toString());
              insurerResponseDto.setInsurerName(insurer.getInsurerName());
              insurerResponseDto.setInsurerAddress(insurer.getInsurerAddress());

              listInsurerResponseDto.add(insurerResponseDto);
            });

    return new PaginationResponse<>(
        listInsurerResponseDto,
        paginatedInsurer.getPageable().getPageNumber(),
        paginatedInsurer.getPageable().getPageSize(),
        paginatedInsurer.getTotalPages(),
        paginatedInsurer.getTotalElements());
  }
}
