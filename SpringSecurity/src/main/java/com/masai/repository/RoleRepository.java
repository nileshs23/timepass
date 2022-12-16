package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Role;
import com.masai.model.RolesEnums;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
    Optional<Role> findByName(RolesEnums name);
}
