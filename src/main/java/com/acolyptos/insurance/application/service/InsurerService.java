package com.acolyptos.insurance.application.service;

import com.acolyptos.insurance.domain.exceptions.EntityAlreadyExistsException;
import com.acolyptos.insurance.domain.exceptions.EntityDoesNotExistException;
import com.acolyptos.insurance.domain.exceptions.InvalidRequestBodyException;
import com.acolyptos.insurance.domain.insurer.Insurer;
import com.acolyptos.insurance.domain.insurer.InsurerRegisterRequest;
import com.acolyptos.insurance.domain.insurer.InsurerRepositoryInterface;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The service class for Insurer Entity, it processes passed arguments/objects before saving it to
 * the database or retrieving an entity from the database.
 */
@Service
public class InsurerService {

  private final InsurerRepositoryInterface insurerRepositoryInterface;

  /**
   * The class constructor of the Insurer Service.
   *
   * @param insurerRepositoryInterface injected class to make the service use its methods for
   *     database.
   */
  public InsurerService(InsurerRepositoryInterface insurerRepositoryInterface) {
    this.insurerRepositoryInterface = insurerRepositoryInterface;
  }

  /**
   * Method that will be used to process the passed object and saves the entity to the databse.
   *
   * @param insurerRegisterRequest passed object to process its property before saving it to the
   *     database.
   * @return {@link Insurer} if the process is successful.
   * @throws EntityAlreadyExistsException if the name property returns an entity after // * checking
   *     from the database.
   */
  public Insurer createInsurer(InsurerRegisterRequest insurerRegisterRequest) {

    boolean exists = checkInsurerIfExists(insurerRegisterRequest.getInsurerName());

    if (exists) {
      throw new EntityAlreadyExistsException("Insurer already exists.");
    }

    Insurer insurer =
        new Insurer(
            insurerRegisterRequest.getInsurerName().trim(),
            insurerRegisterRequest.getInsurerAddress().trim());

    return insurerRepositoryInterface.saveInsurer(insurer);
  }

  /**
   * Method that will be used to find an entity using a passed string.
   *
   * @param insurerName the passed string the will used to look for an existing entity.
   * @return {@link Insurer} if the entity exists.
   * @throws InvalidRequestBodyException if the passed string is invalid.
   * @throws EntityDoesNotExistException if the entity doesn't exist on the database.
   */
  public Insurer getInsurerByName(String insurerName) {

    if (insurerName.trim().isEmpty() || insurerName == null) {
      throw new InvalidRequestBodyException(
          "Insurer name is required before looking for an Insurer.");
    }

    Insurer insurer = insurerRepositoryInterface.findInsurerByInsurerName(insurerName);

    if (insurer == null) {
      throw new EntityDoesNotExistException(
          "The Insurer trying to find using " + insurerName + " does not exist in the database.");
    }

    return insurer;
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
