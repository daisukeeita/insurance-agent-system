package com.acolyptos.insurance.domain.insurer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Repository interface for managing {@link Insurer} entities. */
public interface InsurerRepositoryInterface {

  /**
   * Persists the given {@link Insurer} entity to the database.
   *
   * @param insurer The entity to be saved.
   * @return The saved {@link Insurer} entity.
   */
  Insurer saveInsurer(Insurer insurer);

  /**
   * Finds a single {@link Insurer} entity by its name.
   *
   * @param insurerName The name of the insurer to find.
   * @return An {@link Optional} containing the Insurer if found, otherwise empty.
   */
  Optional<Insurer> getInsurerByInsurerName(String insurerName);

  /**
   * Finds a single {@link Insurer} entity by its unique ID.
   *
   * @param insurerId The unique ID of the insurer to find.
   * @return An {@link Optional} containing the Insurer if found, otherwise empty.
   */
  Optional<Insurer> getInsurerById(UUID insurerId);

  /**
   * Checks if an {@link Insurer} entity with the given unique ID exists in the database.
   *
   * @param insurerId The unique ID of the insurer to check for existence.
   * @return true if an insurer exists with the given unique ID, otherwise false.
   */
  boolean checkInsurerIfExistsById(UUID insurerId);

  /**
   * Checks if an {@link Insurer} entity with the given name exists in the database.
   *
   * @param insurerName The name of the insurer to check for existence.
   * @return true if an insurer exists with the given name, otherwise false.
   */
  boolean checkInsurerIfExistsByInsurerName(String insurerName);

  /**
   * Retrieves a paginated list of all {@link Insurer} entities.
   *
   * @param pageable The pagination information of (number, size, sort).
   * @return A {@link Page} of {@link Insurer} entities.
   */
  Page<Insurer> getPaginatedInsurers(Pageable pageable);

  /**
   * Retrieves all {@link Insurer} entities from the database.
   *
   * @return A list of all {@link Insurer} entities.
   */
  List<Insurer> getAllInsurers();
}
