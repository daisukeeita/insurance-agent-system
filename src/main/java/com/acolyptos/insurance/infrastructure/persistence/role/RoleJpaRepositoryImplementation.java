package com.acolyptos.insurance.infrastructure.persistence.role;

import com.acolyptos.insurance.domain.role.Role;
import com.acolyptos.insurance.domain.role.RoleRepositoryInterface;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RoleJpaRepositoryImplementation implements RoleRepositoryInterface {

  private final RoleJpaRepository roleJpaRepository;

  public RoleJpaRepositoryImplementation(RoleJpaRepository roleJpaRepository) {
    this.roleJpaRepository = roleJpaRepository;
  }

  @Override
  public Optional<Role> findRoleByRoleName(String roleName) {
    return roleJpaRepository.findByRoleName(roleName);
  }

  @Override
  public Optional<Role> findRoleById(Long id) {
    return roleJpaRepository.findById(id);
  }
}
