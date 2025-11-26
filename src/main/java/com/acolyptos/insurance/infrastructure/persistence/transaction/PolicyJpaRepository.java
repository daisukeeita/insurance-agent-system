package com.acolyptos.insurance.infrastructure.persistence.transaction;

import com.acolyptos.insurance.domain.transaction.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyJpaRepository extends JpaRepository<Policy, String> {}
