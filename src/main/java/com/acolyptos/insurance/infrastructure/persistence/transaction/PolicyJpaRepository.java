package com.acolyptos.insurance.infrastructure.persistence.transaction;

import com.acolyptos.insurance.domain.transaction.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA Repository for the {@link Policy} entity, providding standard CRUD and custom
 * lookup methods.
 */
public interface PolicyJpaRepository extends JpaRepository<Policy, String> {}
