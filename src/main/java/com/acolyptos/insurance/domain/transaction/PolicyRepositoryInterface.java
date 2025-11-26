package com.acolyptos.insurance.domain.transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PolicyRepositoryInterface {

  Policy savePolicy(Policy policy);

  List<Policy> saveAllPolicy(List<Policy> listPolicy);

  Optional<Policy> getPolicyById(String policyId);

  Page<Policy> getPaginatedPolicy(Pageable pageable);
}
