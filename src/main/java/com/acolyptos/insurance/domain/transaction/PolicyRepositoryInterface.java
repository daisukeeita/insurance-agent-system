package com.acolyptos.insurance.domain.transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Repository interface for managing {@link Policy} entities. */
public interface PolicyRepositoryInterface {

  /**
   * Persists the given {@link Policy} entity to the database.
   *
   * @param policy The entity to be saved.
   * @return The saved {@link Agent} entity.
   */
  Policy savePolicy(Policy policy);

  /**
   * Finds a single {@link Policy} entity by its unique identification number.
   *
   * @param policyId The unique ID of the policy to find.
   * @return An {@link Optional} containing the {@link Policy} if found, otherwise empty.
   */
  Optional<Policy> findPolicyById(String policyId);

  /**
   * Checks if a {@link Policy} entity with the given unique IID exists in the database.
   *
   * @param policyId The unique ID of the Policy to check for existence.
   * @return 'true' if a policy exists with the given unique ID, otherwise 'false'.
   */
  boolean checkPolicyIfExistsById(String policyId);

  /**
   * Retrieves a paginated list of all {@link Policy} entities.
   *
   * @param pageable The pagination information of the Policy (number, size, sort).
   * @return A {@link Page} of {@link Policy} entities.
   */
  Page<Policy> getPaginatedPolicy(Pageable pageable);

  /**
   * Retrieves all {@link Policy} entities from the database.
   *
   * @return A list of all {@link Policy} entities.
   */
  List<Policy> findAllPolicy();
}
