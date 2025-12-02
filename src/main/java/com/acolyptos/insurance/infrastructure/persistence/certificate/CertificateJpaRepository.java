package com.acolyptos.insurance.infrastructure.persistence.certificate;

import com.acolyptos.insurance.domain.certificate.CertificateOfCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link CertificateOfCoverage} entity, providing standard CRUD
 * and custome lookup methods.
 */
public interface CertificateJpaRepository extends JpaRepository<CertificateOfCoverage, String> {}
