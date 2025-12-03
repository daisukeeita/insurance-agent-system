package com.acolyptos.insurance.domain.certificate;

import java.util.Optional;

/** This will be used for the status of {@link CertificateOfCoverage} entity. */
public enum CertificateStatus {
  AVAILABLE,
  PENDING,
  ACTIVE,
  VOID;

  /**
   * Converts a case-insensitive status string into a {@code CertificateStatus} Enum.
   *
   * @param status The string representation of status.
   * @return An {@link Optional} containing the matching {@code CertificateStatus}, otherwise empty.
   */
  public static Optional<CertificateStatus> fromString(String status) {
    if (status == null) {
      return Optional.empty();
    }

    for (CertificateStatus certificateStatus : CertificateStatus.values()) {
      if (certificateStatus.name().equalsIgnoreCase(status.trim())) {
        return Optional.of(certificateStatus);
      }
    }

    return Optional.empty();
  }
}
