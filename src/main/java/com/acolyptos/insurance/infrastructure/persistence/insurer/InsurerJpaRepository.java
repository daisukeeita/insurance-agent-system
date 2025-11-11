package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface class that extends the {@link JpaRepository} to be able to use simplified data access
 * methods.
 */
public interface InsurerJpaRepository extends JpaRepository<Insurer, UUID> {

  /**
   * Custom Jpa repository method that saves an entity.
   *
   * @param insurer passed object that will be used to save an entity.
   * @return {@link Insurer} if the process is successful.
   */
  Insurer save(Insurer insurer);

  /**
   * Custom Jpa repository method that finds an entity using the passed string.
   *
   * @param insurerName passed string that will be used to find the entity from the database.
   * @return {@link Insurer} if the process is successful, else null.
   */
  Insurer findByInsurerName(String insurerName);
}
