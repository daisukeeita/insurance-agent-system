package com.acolyptos.insurance.domain.insurer;

import java.util.List;
import java.util.Optional;

public interface InsurerRepositoryInterface {

  Insurer saveInsurer(Insurer insurer);

  Optional<Insurer> findInsurerByInsurerName(String insurerName);

  List<Insurer> findAllInsurers();
}
