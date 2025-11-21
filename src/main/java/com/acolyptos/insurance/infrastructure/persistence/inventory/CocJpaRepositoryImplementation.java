package com.acolyptos.insurance.infrastructure.persistence.inventory;

import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import com.acolyptos.insurance.domain.inventory.CocRepositoryInterface;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CocJpaRepositoryImplementation implements CocRepositoryInterface {

  private final CocJpaRepository cocJpaRepository;

  public CocJpaRepositoryImplementation(CocJpaRepository cocJpaRepository) {
    this.cocJpaRepository = cocJpaRepository;
  }

  @Override
  public CertificateOfCoverage saveCertificateOfCoverage(
      CertificateOfCoverage certificateOfCoverage) {
    return cocJpaRepository.save(certificateOfCoverage);
  }

  @Override
  public CertificateOfCoverage getCertificateOfCoverageByNumber(String cocNumber) {
    return cocJpaRepository.getReferenceById(cocNumber);
  }

  @Override
  public List<CertificateOfCoverage> getAllCertificateOfCoverage() {
    return cocJpaRepository.findAll();
  }

  @Override
  public List<CertificateOfCoverage> saveAllCertificateOfCoverage(
      List<CertificateOfCoverage> listCertificateOfCoverage) {
    return cocJpaRepository.saveAll(listCertificateOfCoverage);
  }

  @Override
  public Page<CertificateOfCoverage> getPaginatedCertificateOfCoverage(Pageable pageable) {
    return cocJpaRepository.findAll(pageable);
  }

  // @Override
  // public CertificateOfCoverage updateStatusOfCertificate() {
  //   // TODO Auto-generated method stub
  //   throw new UnsupportedOperationException("Unimplemented method 'updateStatusOfCertificate'");
  // }
  //
}
