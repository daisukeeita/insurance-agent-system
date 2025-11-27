package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import com.acolyptos.insurance.domain.insurer.InsurerRequestDto;
import com.acolyptos.insurance.domain.insurer.InsurerResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InsurerService {

  private final InsurerRepositoryInterface insurerRepositoryInterface;

  public InsurerService(InsurerRepositoryInterface insurerRepositoryInterface) {
    this.insurerRepositoryInterface = insurerRepositoryInterface;
  }

  public InsurerResponseDto createInsurer(InsurerRequestDto insurerRequestDto) {

    String insurerName = insurerRequestDto.getInsurerName().trim();

    boolean insurerDoesExists =
        insurerRepositoryInterface.checkInsurerIfExistsByInsurerName(insurerName);

    if (insurerDoesExists) {
      throw new EntityAlreadyExistsException(
          "The Insurer: '" + insurerName + "' already exists in the database.'");
    }

    Insurer insurer =
        new Insurer(insurerRequestDto.getInsurerName(), insurerRequestDto.getInsurerAddress());

    Insurer savedInsurer = insurerRepositoryInterface.saveInsurer(insurer);

    InsurerResponseDto insurerResponseDto = new InsurerResponseDto();
    insurerResponseDto.setInsurerId(savedInsurer.getInsurerId().toString());
    insurerResponseDto.setInsurerName(savedInsurer.getInsurerName());
    insurerResponseDto.setInsurerAddress(savedInsurer.getInsurerAddress());

    return insurerResponseDto;
  }

  public InsurerResponseDto retrieveInsurerByName(String insurerName) {

    if (insurerName == null || insurerName.trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "Insurer Name is required to find and retrieve the data.");
    }

    InsurerResponseDto insurerResponseDto = new InsurerResponseDto();

    return insurerResponseDto;
  }

  public List<Insurer> getAllInsurer() {
    return insurerRepositoryInterface.findAllInsurers();
  }

  private boolean checkInsurerIfExists(String insurerName) {

    Insurer insurer = insurerRepositoryInterface.findInsurerByInsurerName(insurerName);

    if (insurer != null) {
      return true;
    } else {
      return false;
    }
  }
}
