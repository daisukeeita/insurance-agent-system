package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InsurerJpaRepositoryImplementation implements InsurerRepositoryInterface {

  private final InsurerJpaRepository insurerJpaRepository;

  public InsurerJpaRepositoryImplementation(InsurerJpaRepository insurerJpaRepository) {
    this.insurerJpaRepository = insurerJpaRepository;
  }

  @Override
  public Optional<Insurer> findInsurerByInsurerName(String insurerName) {
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
