package com.acolyptos.insurance.entitiestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.acolyptos.insurance.domain.insurer.Insurer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Testing the validity of Insurer's properties. */
@SpringBootTest
public class InsurerTest {

  @Autowired private static Validator validator;

  @BeforeAll
  static void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @DisplayName("Should pass validation foor a valid Insurer entity.")
  @Test
  void shouldPassValidationForValidInsurerEntity() {
    Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
    insurer.setInsurerId(UUID.randomUUID());

    final Set<ConstraintViolation<Insurer>> violations = validator.validate(insurer);

    if (!violations.isEmpty()) {
      final StringBuilder error =
          new StringBuilder("Expected no violations, but found: " + violations.size() + "\n");
      for (final ConstraintViolation<Insurer> violation : violations) {
        error
            .append(" - ")
            .append(violation.getPropertyPath())
            .append(": ")
            .append(violation.getMessage())
            .append(" (Invalid Value: '")
            .append(violation.getInvalidValue())
            .append("')\n");
      }

      fail(error.toString());
    }
  }

  @DisplayName("Should throw an error message for empty or null insurer name.")
  @Test
  void shouldThrowErrorMessageForEmptyOrNullInsurerName() {
    Insurer insurer = new Insurer("", "Doe's Insurer Address");
    insurer.setInsurerId(UUID.randomUUID());

    final Set<ConstraintViolation<Insurer>> violations = validator.validate(insurer);

    assertFalse(violations.isEmpty());
    assertEquals(1, violations.size());

    final ConstraintViolation<Insurer> violation = violations.iterator().next();
    assertEquals("insurerName", violation.getPropertyPath().toString());
    assertTrue(violation.getMessage().contains("Name of the Insurer should not be blank."));
  }

  @DisplayName("Should throw an error message for empty or null insurer address")
  @Test
  void shouldThrowErrorMessageForEmptyOrNullInsurerAddress() {
    Insurer insurer = new Insurer("Doe's Insurance", "");
    insurer.setInsurerId(UUID.randomUUID());

    final Set<ConstraintViolation<Insurer>> violations = validator.validate(insurer);

    assertFalse(violations.isEmpty());
    assertEquals(1, violations.size());

    final ConstraintViolation<Insurer> violation = violations.iterator().next();
    assertEquals("insurerAddress", violation.getPropertyPath().toString());
    assertTrue(violation.getMessage().contains("Address of the Insurer should not be blank."));
  }
}
