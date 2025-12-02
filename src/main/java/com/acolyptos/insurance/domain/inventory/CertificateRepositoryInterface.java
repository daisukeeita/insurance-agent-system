package com.acolyptos.insurance.domain.inventory;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Repository interface for managing {@link CertificateOfCoverage} entities. */
public interface CertificateRepositoryInterface {

  /**
   * Persists the given {@link CertificateOfCoverage} entity to the database.
   *
   * @param certificateOfCoverage The entity to be saved.
   * @return The saved {@link CertificateOfCoverage} entity.
   */
  CertificateOfCoverage saveCertificateOfCoverage(CertificateOfCoverage certificateOfCoverage);

  /**
   * Persists the given list of {@link CertificateOfCoverage} entity to the database.
   *
   * @param listCertificateOfCoverage The list of entities to be saved.
   * @return A {@link List} of {@link CertificateOfCoverage}.
   */
  List<CertificateOfCoverage> saveAllCertificateOfCoverage(
      List<CertificateOfCoverage> listCertificateOfCoverage);

  /**
   * Finds a single {@link CertificateOfCoverage} entity by its unique identification.
   *
   * @param cocNumber The unique identification of the Certificate of Coverage to find.
   * @return An {@link Optional} containing the Certificate of Coverage if found, otherwise empty.
   */
  Optional<CertificateOfCoverage> getCertificateOfCoverageByNumber(String cocNumber);

  /**
   * Retrieves a paginated list of all {@link CertificateOfCoverage} entities.
   *
   * @param pageable The pagination information of the certificate (number, size, sort).
   * @return A {@link Page} of {@link CertificateOfCoverage} entities.
   */
  Page<CertificateOfCoverage> getPaginatedCertificateOfCoverage(Pageable pageable);

  /**
   * Retrieves all {@link CertificateOfCoverage} entities from the database.
   *
   * @return A list of all {@link CertificateOfCoverage} entities.
   */
  List<CertificateOfCoverage> getAllCertificateOfCoverage();
}
