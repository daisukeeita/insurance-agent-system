package com.acolyptos.insurance.infrastructure.persistence.transaction;

import com.acolyptos.insurance.domain.transaction.Policy;
import com.acolyptos.insurance.domain.transaction.PolicyRepositoryInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyJpaRepositoryImplementation implements PolicyRepositoryInterface {

  private final PolicyJpaRepository policyJpaRepository;

  public PolicyJpaRepositoryImplementation(PolicyJpaRepository policyJpaRepository) {
    this.policyJpaRepository = policyJpaRepository;
  }

  @Override
  public Policy savePolicy(Policy policy) {
    return policyJpaRepository.save(policy);
  }

  @Override
  public List<Policy> saveAllPolicy(List<Policy> listPolicy) {
    return policyJpaRepository.saveAll(listPolicy);
  }

  @Override
  public Optional<Policy> getPolicyById(String policyId) {
    return policyJpaRepository.findById(policyId);
  }

  @Override
  public Page<Policy> getPaginatedPolicy(Pageable pageable) {
    return policyJpaRepository.findAll(pageable);
  }
}
