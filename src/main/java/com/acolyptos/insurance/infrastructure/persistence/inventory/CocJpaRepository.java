package com.acolyptos.insurance.infrastructure.persistence.inventory;

import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocJpaRepository extends JpaRepository<CertificateOfCoverage, String> {

  List<CertificateOfCoverage> getAllCertificates();
}
