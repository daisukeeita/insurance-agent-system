package com.acolyptos.insurance.infrastructure.persistence.inventory;

import com.acolyptos.insurance.domain.inventory.CertificateOfCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link CertificateOfCoverage} entity, providing standard CRUD
 * and custome lookup methods.
 */
public interface CertificateJpaRepository extends JpaRepository<CertificateOfCoverage, String> {}
