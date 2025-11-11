package com.acolyptos.insurance.domain.insurer;

import java.util.List;

/** The 'blueprint' of the insurer repository. */
public interface InsurerRepositoryInterface {

  /**
   * Method that will be used to save the Insurer Entity to the database.
   *
   * @param insurer passed object that will be saved to the databse.
   * @return {@link Insurer} if the object passed was saved successfully.
   */
  Insurer saveInsurer(Insurer insurer);

  /**
   * Method that will be used to find a single Insurer Entity from the database.
   *
   * @param insurerName passed string that will be used to find an Insurer.
   * @return {@link Insurer} if the Insurer exists, else null.
   */
  Insurer findInsurerByInsurerName(String insurerName);

  /**
   * Method that will be used to find all of the insurers saved in the databse.
   *
   * @return lists of all {@link Insurer}, else an empty list.
   */
  List<Insurer> findAllInsurers();
}
