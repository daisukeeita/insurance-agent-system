package com.acolyptos.insurance.infrastructure.persistence.transaction;

import com.acolyptos.insurance.domain.transaction.Policy;
import com.acolyptos.insurance.domain.transaction.PolicyRepositoryInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * The implementation of the {@link PolicyRepositoryInterface} that uses {@link PolicyJpaRepository}
 * to interact with the database.
 */
@Repository
public class PolicyJpaRepositoryImplementation implements PolicyRepositoryInterface {

  private final PolicyJpaRepository policyJpaRepository;

  /**
   * Constructs the repository implementation and injects the necessry JPA component.
   *
   * @param policyJpaRepository The Spring Data JPA repository used from direct database access.
   */
  public PolicyJpaRepositoryImplementation(final PolicyJpaRepository policyJpaRepository) {
    this.policyJpaRepository = policyJpaRepository;
  }

  @Override
  public Policy savePolicy(final Policy policy) {
    return policyJpaRepository.save(policy);
  }

  @Override
  public Optional<Policy> findPolicyById(final String policyId) {
    return policyJpaRepository.findById(policyId);
  }

  @Override
  public Optional<Policy> findPolicyByCocNumber(final String cocNumber) {
    return policyJpaRepository.findByCocNumber(cocNumber);
  }

  @Override
  public boolean checkPolicyIfExistsById(final String policyId) {
    return policyJpaRepository.existsById(policyId);
  }

  @Override
  public Page<Policy> getPaginatedPolicy(final Pageable pageable) {
    return policyJpaRepository.findAll(pageable);
  }

  @Override
  public List<Policy> findAllPolicy() {
    return policyJpaRepository.findAll();
  }
}
