package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InsurerService;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRequestDto;
import com.acolyptos.insurance.domain.insurer.InsurerResponseDto;
import com.acolyptos.insurance.domain.response.PaginationResponse;
import com.acolyptos.insurance.domain.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling incoming HTTP requests related to {@link Insurer} operations.
 *
 * <p>It maps REST endpoints to corresponding service methods.
 */
@RestController
@RequestMapping("/api/v1/insurer")
public class InsurerController {

  private final InsurerService insurerService;

  /**
   * Constructs an {@code InsurerController} with required service dependency.
   *
   * @param insurerService The service class implementing the business logic for Insurer operations.
   */
  public InsurerController(final InsurerService insurerService) {
    this.insurerService = insurerService;
  }

  /**
   * Creates a new Insurer record.
   *
   * <p>Accepts an {@link InsurerRequestDto} from the client, validates it, and sends it to the
   * service layer for persistence.
   *
   * @param insurerRequestDto The DTO containing the details of the insurer to be created. It is
   *     bound from the request body using {@code @RequestBody} and validated using {@code @Valid}.
   * @return A {@link SuccessResponse} containing the created {@link InsurerResponseDto} and an HTTP
   *     status of {@link HttpStatus#CREATED} (201).
   * @throws RuntimeException Various exceptions handled globally, typically returning an {@code
   *     ErrorResponse} JSON body.
   */
  @PostMapping("/createInsurer")
  @ResponseStatus(HttpStatus.CREATED)
  public SuccessResponse<InsurerResponseDto> createAndSaveInsurer(
      @RequestBody @Valid final InsurerRequestDto insurerRequestDto) {

    final InsurerResponseDto insurer = insurerService.createInsurer(insurerRequestDto);
    return new SuccessResponse<InsurerResponseDto>(
        HttpStatus.CREATED.value(), HttpStatus.CREATED, "Insurer created successfully.", insurer);
  }

  /**
   * Retrieves a specific {@link Insurer} entity based in its name.
   *
   * @param insurerName The name of the insurer to retrieve. This value is extracted from the URL
   *     path using {@code @PathVariable}.
   * @return The {@link SuccessResponse} containing the found {@link InsurerRequestDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typicall returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getInsurerByName/{insurerName}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<InsurerResponseDto> findAndRetrieveInsurerByName(
      @PathVariable("insurerName") final String insurerName) {

    final InsurerResponseDto insurer = insurerService.retrieveInsurerByName(insurerName);
    return new SuccessResponse<InsurerResponseDto>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Reqeusted insurer successfully found.",
        insurer);
  }

  /**
   * Retrieves a specific {@link Insurer} entity based in its unique identification.
   *
   * @param insurerId The UUID (as a String) of the insurer to retrieve. This value is extracted
   *     from the URL path using {@code @PathVariable}.
   * @return The {@link SuccessResponse} containing the found {@link InsurerRequestDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally, typicall returning an {@code
   *     ErrorResponse} JSON body.
   */
  @GetMapping("/getInsurerById/{insurerId}")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<InsurerResponseDto> findAndRetrieveInsurerById(
      @PathVariable("insurerId") final String insurerId) {

    final InsurerResponseDto insurer = insurerService.retrieveInsurerById(insurerId);
    return new SuccessResponse<InsurerResponseDto>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Reqeusted insurer successfully found.",
        insurer);
  }

  /**
   * Retrieves a paginated list of all Insurers.
   *
   * @param pageNumber The zero-based index of the page to retrieve. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @param pageSize The maximum number of insurers to include in the page. Extracted from URL query
   *     parameters using {@code @RequestParam}.
   * @return The {@link SuccessResponse} containing the found {@link InsurerRequestDto} and an HTTP
   *     status of {@link HttpStatus#FOUND} (302).
   * @throws RuntimeException Various exceptions handled globally.
   */
  @GetMapping("/getPaginatedInsurers")
  @ResponseStatus(HttpStatus.FOUND)
  public SuccessResponse<PaginationResponse<InsurerResponseDto>> retrievePaginatedInsurers(
      @RequestParam(defaultValue = "0") final int pageNumber,
      @RequestParam(defaultValue = "10") final int pageSize) {

    final PaginationResponse<InsurerResponseDto> paginatedInsurers =
        insurerService.retrievePaginatedInsurers(pageNumber, pageSize);

    return new SuccessResponse<PaginationResponse<InsurerResponseDto>>(
        HttpStatus.FOUND.value(),
        HttpStatus.FOUND,
        "Successfully found and paginated insurers.",
        paginatedInsurers);
  }
}
