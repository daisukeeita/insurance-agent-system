package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRegisterRequest;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import org.springframework.stereotype.Service;

@Service
public class InsurerService {

  private final InsurerRepositoryInterface insurerRepositoryInterface;

  public InsurerService(InsurerRepositoryInterface insurerRepositoryInterface) {
    this.insurerRepositoryInterface = insurerRepositoryInterface;
  }

  public Insurer createInsurer(InsurerRegisterRequest insurerRegisterRequest) {
    Insurer insurer =
        new Insurer(
            insurerRegisterRequest.getInsurerName(), insurerRegisterRequest.getInsurerAddress());

    return insurerRepositoryInterface.saveInsurer(insurer);
  }

  // public Insurer getInsurerByName(String insurerName) {
  //   return insurerRepositoryInterface.findInsurerByInsurerName(insurerName).orElseThrow();
  // }
  //
  // public List<Insurer> getAllInsurer() {
  //   return insurerRepositoryInterface.findAllInsurers();
  // }
  //
  // private boolean checkInsurerIfExists(String insurerName) {
  //   Optional<Insurer> insurer = insurerRepositoryInterface.findInsurerByInsurerName(insurerName);
  //
  //   if (insurer != null) {
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }
}
