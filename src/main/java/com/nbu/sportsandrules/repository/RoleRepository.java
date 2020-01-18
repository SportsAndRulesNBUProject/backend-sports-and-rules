package com.nbu.sportsandrules.repository;

import com.nbu.sportsandrules.entity.ERole;
import com.nbu.sportsandrules.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(ERole name);
}
