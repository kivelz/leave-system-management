package com.leave.system.service;

import java.util.List;
import java.util.Optional;

import com.leave.system.model.Role;

public interface RoleServiceIF {

	List<Role> findAll();
	
	Role saveRole(Role role);
	
	Optional<Role> findById(int id);

}