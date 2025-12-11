package com.acolyptos.insurance.domain.role;

import java.util.Optional;

public interface RoleRepositoryInterface {

  Optional<Role> findRoleByRoleName(String roleName);

  Optional<Role> findRoleById(Long id);
}
