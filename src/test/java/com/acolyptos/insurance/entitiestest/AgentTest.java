// package com.acolyptos.insurance.entitiestest;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
//
// import com.acolyptos.insurance.domain.agent.Agent;
// import com.acolyptos.insurance.domain.insurer.Insurer;
// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;
// import java.time.LocalDate;
// import java.util.Set;
// import java.util.UUID;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// /** Testing the validity of Agent's properties. */
// @SpringBootTest
// public class AgentTest {
//
//   @Autowired private static Validator validator;
//
//   @BeforeAll
//   static void setup() {
//     final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//     validator = factory.getValidator();
//   }
//
//   @DisplayName("Should pass validation for a valid Agent entity.")
//   @Test
//   void shouldPassValidationForValidAgentEntity() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent(
//             "johndoe",
//             "$@$SDGSDfa;sd12288",
//             insurer,
//             "John",
//             "D.",
//             "Doe",
//             "LICENSE-1234",
//             LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     if (!violations.isEmpty()) {
//       final StringBuilder error =
//           new StringBuilder("Expected no violations, but found: " + violations.size() + "\n");
//       for (final ConstraintViolation<Agent> violation : violations) {
//         error
//             .append(" - ")
//             .append(violation.getPropertyPath())
//             .append(": ")
//             .append(violation.getMessage())
//             .append(" (Invalid Value: '")
//             .append(violation.getInvalidValue())
//             .append("')\n");
//       }
//
//       fail(error.toString());
//     }
//   }
//
//   @DisplayName("Should throw an error message for empty or null username")
//   @Test
//   void shouldThrowErrorMessageForEmptyOrNullUsername() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent(
//             "",
//             "$@$SDGSDfa;sd12288",
//             insurer,
//             "John",
//             "D.",
//             "Doe",
//             "LICENSE-1234",
//             LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("username", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `username` was not provided."));
//   }
//
//   @DisplayName("Should throw an error message for empty or null hashed password")
//   @Test
//   void shouldThrowErrorMessageForEmptyOrNullPassword() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent("johndoe", null, insurer, "John", "D.", "Doe", "LICENSE-1234",
// LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("hashedPassword", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `hashed password` was not provided."));
//   }
//
//   @DisplayName("Should throw an error message for empty or null first name")
//   @Test
//   void shouldThrowErrorMessageForEmptyOrNullFirstName() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent(
//             "johndoe",
//             "$!@##SDFGdfg2938",
//             insurer,
//             "",
//             "D.",
//             "Doe",
//             "LICENSE-1234",
//             LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("firstName", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `first name` was not provided."));
//   }
//
//   @DisplayName("Should throw an error message for emtpy or null last name")
//   @Test
//   void shouldThrowErrorMessageForEmptyOrNullLastName() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent(
//             "johndoe",
//             "$!@##SDFGdfg2938",
//             insurer,
//             "John",
//             "D.",
//             "",
//             "LICENSE-1234",
//             LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("lastName", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `last name` was not provided."));
//   }
//
//   @DisplayName("Should throw an error message for empty or null license number.")
//   @Test
//   void shouldThrowErrorMessageForEmptyOrNullLicenseNumber() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent("johndoe", "$!@##SDFGdfg2938", insurer, "John", "D.", "Doe", "",
// LocalDate.now());
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("licenseNumber", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `license number` was not provided."));
//   }
//
//   @DisplayName("Should throw an error message for null date hired.")
//   @Test
//   void shouldThrowErrorMessageForNullDateHired() {
//     final Insurer insurer = new Insurer("Doe's Insurer", "Doe's Insurer Address");
//     insurer.setInsurerId(UUID.randomUUID());
//     final Agent agent =
//         new Agent(
//             "johndoe", "$!@##SDFGdfg2938", insurer, "John", "D.", "Doe", "LICENSE-1234", null);
//     agent.setAgentId(UUID.randomUUID());
//
//     final Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//
//     assertFalse(violations.isEmpty());
//     assertEquals(1, violations.size());
//
//     final ConstraintViolation<Agent> violation = violations.iterator().next();
//     assertEquals("dateHired", violation.getPropertyPath().toString());
//     assertTrue(violation.getMessage().contains("Agent's `date hired` was not provided."));
//   }
// }
