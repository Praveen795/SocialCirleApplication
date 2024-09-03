package com.socialCircle.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialCircle.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role>  findByRoleName(String roleName);

}
