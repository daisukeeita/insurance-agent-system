package com.acolyptos.insurance.infrastructure.persistence.role;

import com.acolyptos.insurance.domain.role.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByRoleName(String roleName);
}
