package com.acolyptos.insurance.domain.inventory;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CocRepositoryInterface {

  CertificateOfCoverage saveCertificateOfCoverage(CertificateOfCoverage certificateOfCoverage);

  List<CertificateOfCoverage> saveAllCertificateOfCoverage(
      List<CertificateOfCoverage> listCertificateOfCoverage);

  CertificateOfCoverage getCertificateOfCoverageByNumber(String cocNumber);

  List<CertificateOfCoverage> getAllCertificateOfCoverage();

  Page<CertificateOfCoverage> getPaginatedCertificateOfCoverage(Pageable pageable);

  // CertificateOfCoverage updateStatusOfCertificate();
}
