package com.acolyptos.insurance.infrastructure.persistence.insurer;

import com.acolyptos.insurance.domain.insurer.Insurer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurerJpaRepository extends JpaRepository<Insurer, UUID> {

  Insurer save(Insurer insurer);

  Insurer findByInsurerName(String insurerName);
}
