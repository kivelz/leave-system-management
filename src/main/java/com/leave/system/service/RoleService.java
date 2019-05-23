package com.leave.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.system.model.Role;
import com.leave.system.repository.RoleRepository;

@Service
public class RoleService implements RoleServiceIF {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll() {
		List<Role> roles = roleRepository.findAll();
		return roles;
	}

}
