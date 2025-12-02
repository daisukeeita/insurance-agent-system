package com.acolyptos.insurance.infrastructure.persistence.certificate;

import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
import com.acolyptos.insurance.domain.certificate.CertificateRepositoryInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * The implementation of the {@link CertificateRepositoryInterface} that uses {@link
 * CertificateJpaRepository} to interact with the database.
 */
@Repository
public class CertificateJpaRepositoryImplementation implements CertificateRepositoryInterface {

  private final CertificateJpaRepository certificateJpaRepository;

  /**
   * Constructs the repository implementation and injects to necessary JPA component.
   *
   * @param certificateJpaRepository The Spring Data JPA repository used for direct database access.
   */
  public CertificateJpaRepositoryImplementation(CertificateJpaRepository certificateJpaRepository) {
    this.certificateJpaRepository = certificateJpaRepository;
  }

  @Override
  public CertificateOfCoverage saveCertificateOfCoverage(
      CertificateOfCoverage certificateOfCoverage) {
    return certificateJpaRepository.save(certificateOfCoverage);
  }

  @Override
  public List<CertificateOfCoverage> saveAllCertificateOfCoverage(
      List<CertificateOfCoverage> listCertificateOfCoverage) {
    return certificateJpaRepository.saveAll(listCertificateOfCoverage);
  }

  @Override
  public Optional<CertificateOfCoverage> getCertificateOfCoverageById(String cocNumber) {
    return certificateJpaRepository.findById(cocNumber);
  }

  @Override
  public boolean checkCertificateIfExistsById(String cocNumber) {
    return certificateJpaRepository.existsById(cocNumber);
  }

  @Override
  public Page<CertificateOfCoverage> getPaginatedCertificateOfCoverage(Pageable pageable) {
    return certificateJpaRepository.findAll(pageable);
  }

  @Override
  public List<CertificateOfCoverage> getAllCertificateOfCoverage() {
    return certificateJpaRepository.findAll();
  }
}
