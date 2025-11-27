package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * The implementation of the {@link InsurerRepositoryInterface} that uses {@link
 * InsurerJpaRepository} to interact with the database.
 */
@Repository
public class InsurerJpaRepositoryImplementation implements InsurerRepositoryInterface {

  private final InsurerJpaRepository insurerJpaRepository;

  /**
   * Constructs the repository implementation and injects the necessary JPA component.
   *
   * @param insurerJpaRepository The Spring Data JPA repository used for direct database access.
   */
  public InsurerJpaRepositoryImplementation(InsurerJpaRepository insurerJpaRepository) {
    this.insurerJpaRepository = insurerJpaRepository;
  }

  @Override
  public Insurer saveInsurer(Insurer insurer) {
    return insurerJpaRepository.save(insurer);
  }

  @Override
  public Optional<Insurer> getInsurerByInsurerName(String insurerName) {
    return insurerJpaRepository.findByInsurerName(insurerName);
  }

  @Override
  public Optional<Insurer> getInsurerById(UUID insurerId) {
    return insurerJpaRepository.findById(insurerId);
  }

  @Override
  public boolean checkInsurerIfExistsById(UUID insurerId) {
    return insurerJpaRepository.existsById(insurerId);
  }

  @Override
  public boolean checkInsurerIfExistsByInsurerName(String insurerName) {
    return insurerJpaRepository.existsByInsurerName(insurerName);
  }

  @Override
  public Page<Insurer> getPaginatedInsurers(Pageable pageable) {
    return insurerJpaRepository.findAll(pageable);
  }

  @Override
  public List<Insurer> getAllInsurers() {
    return insurerJpaRepository.findAll();
  }
}
