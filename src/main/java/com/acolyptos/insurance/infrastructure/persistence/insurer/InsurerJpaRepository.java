package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link Insurer} entity, providing standard CRUD and custom
 * lookup methods.
 */
public interface InsurerJpaRepository extends JpaRepository<Insurer, UUID> {

  /**
   * Finds an {@link Insurer} entity by its name.
   *
   * @param insurerName The name of the insurer to search for.
   * @return An {@link Optional} containing the Insurer if found, otherwise empty.
   */
  Optional<Insurer> findByInsurerName(String insurerName);

  /**
   * Checks if an {@link Insurer} entity exists with the given name.
   *
   * @param insurerName The name of the insurer to check for existence.
   * @return true if an Insurer with that name exists, otherwise false.
   */
  boolean existsByInsurerName(String insurerName);
}
