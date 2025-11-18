package com.acolyptos.insurance.infrastructure.persistence.inventory;

import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import com.acolyptos.insurance.domain.inventory.CocRepositoryInterface;
import java.util.List;
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

  // @Override
  // public CertificateOfCoverage getCertificateOfCoverageByNumber(String cocNumber) {
  //   return cocJpaRepository.getReferenceById(cocNumber);
  // }
  //
  // @Override
  // public CertificateOfCoverage updateStatusOfCertificate() {
  //   // TODO Auto-generated method stub
  //   throw new UnsupportedOperationException("Unimplemented method 'updateStatusOfCertificate'");
  // }
  //
  @Override
  public List<CertificateOfCoverage> getAllCertificateOfCoverage() {
    return cocJpaRepository.getAllCertificates();
  }
}
