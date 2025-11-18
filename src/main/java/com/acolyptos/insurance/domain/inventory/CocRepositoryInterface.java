package com.acolyptos.insurance.domain.inventory;

import java.util.List;

public interface CocRepositoryInterface {

  CertificateOfCoverage saveCertificateOfCoverage(CertificateOfCoverage certificateOfCoverage);

  // CertificateOfCoverage getCertificateOfCoverageByNumber(String cocNumber);
  //
  // CertificateOfCoverage updateStatusOfCertificate();
  //
  List<CertificateOfCoverage> getAllCertificateOfCoverage();
}
