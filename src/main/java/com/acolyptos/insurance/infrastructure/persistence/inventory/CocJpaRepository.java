package com.acolyptos.insurance.infrastructure.persistence.inventory;

import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocJpaRepository extends JpaRepository<CertificateOfCoverage, String> {}
