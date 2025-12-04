package com.acolyptos.insurance.infrastructure.persistence.transaction;

import com.acolyptos.insurance.domain.transaction.Policy;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link Policy} entity, providding standard CRUD and custom
 * lookup methods.
 */
public interface PolicyJpaRepository extends JpaRepository<Policy, String> {

  /**
   * Checks if a {@link Policy} entity exists wit the given linked COC Number.
   *
   * @param cocNumber The COC Number of the certificate linked to the policy to search for.
   * @return An {@link Optional} containing the {@link Policy} if found, otherwise empty.
   */
  Optional<Policy> findByCocNumber(String cocNumber);
}
