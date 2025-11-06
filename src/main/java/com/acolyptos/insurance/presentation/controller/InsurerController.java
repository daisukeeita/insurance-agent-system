package com.acolyptos.insurance.presentation.controller;

import com.acolyptos.insurance.application.service.InsurerService;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/insurer")
public class InsurerController {

  private final InsurerService insurerService;

  public InsurerController(InsurerService insurerService) {
    this.insurerService = insurerService;
  }

  @PostMapping("/createInsurer")
  public Insurer createAndSaveInsurer(@RequestBody InsurerRegisterRequest insurerRegisterRequest) {
    return insurerService.createInsurer(insurerRegisterRequest);
  }
}
