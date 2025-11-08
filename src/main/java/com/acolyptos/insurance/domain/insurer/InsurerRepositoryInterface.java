package com.acolyptos.insurance.domain.insurer;

import java.util.List;

public interface InsurerRepositoryInterface {

  Insurer saveInsurer(Insurer insurer);

  Insurer findInsurerByInsurerName(String insurerName);

  List<Insurer> findAllInsurers();
}
