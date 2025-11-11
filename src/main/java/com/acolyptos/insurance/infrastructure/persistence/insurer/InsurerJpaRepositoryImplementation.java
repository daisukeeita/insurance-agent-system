package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * The repository class that implements the {@link InsurerRepositoryInterface} to follow the
 * 'blueprint' and injects the {@link InsurerJpaRepository} to be able to access the database
 * easily.
 */
@Repository
public class InsurerJpaRepositoryImplementation implements InsurerRepositoryInterface {

  private final InsurerJpaRepository insurerJpaRepository;

  /**
   * Class constructor of the repository class.
   *
   * @param insurerJpaRepository injected Jpa repository interface to use the simplified methods.
   */
  public InsurerJpaRepositoryImplementation(InsurerJpaRepository insurerJpaRepository) {
    this.insurerJpaRepository = insurerJpaRepository;
  }

  @Override
  public Insurer findInsurerByInsurerName(String insurerName) {
    return insurerJpaRepository.findByInsurerName(insurerName);
  }

  @Override
  public Insurer saveInsurer(Insurer insurer) {
    return insurerJpaRepository.save(insurer);
  }

  @Override
  public List<Insurer> findAllInsurers() {
    return insurerJpaRepository.findAll();
  }
}
