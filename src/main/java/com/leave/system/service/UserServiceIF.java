//package com.leave.system.service;
//
//import java.util.ArrayList;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.leave.system.model.Role;
//import com.leave.system.model.User;
//import com.leave.system.repository.UserRepository;
//
//@Service
//public class UserServiceIF implements UserService {
//
//	@Resource
//	private UserRepository userRepository;
//	
//	@Override
//	@Transactional
//	public ArrayList<User> findAllUsers() {
//		ArrayList<User> ul = (ArrayList<User>) userRepository.findAll();
//		return ul;
//	}
//	
//	@Override
//	@Transactional
//	public User findUser(String userId) {
//		return userRepository.findById(userId).orElse(null);
//
//	}
//
//	@Override
//	@Transactional
//	public User createUser(User user) {
//		return userRepository.saveAndFlush(user);
//	}
//
//	@Override
//	@Transactional
//	public User changeUser(User user) {
//		return userRepository.saveAndFlush(user);
//	}
//
//	@Override
//	@Transactional
//	public void removeUser(User user) {
//		userRepository.delete(user);
//	}
//	
//	@Override
//	@Transactional
//	public ArrayList<Role> findRolesForUser(String userId) {
//		return (ArrayList<Role>)userRepository.findById(userId).orElse(null).getRoleSet();
//	}
//	
//	@Override
//	@Transactional
//	public ArrayList<String> findRoleNamesForUser(String userId) {
//		ArrayList<Role> rset =  (ArrayList<Role>) userRepository.findById(userId).orElse(null).getRoleSet();
//		ArrayList<String> rnames = new ArrayList<String>();
//		for (Role role : rset) {
//			rnames.add(role.getName());
//		}
//		return rnames;
//	}
//	
//	@Override
//	@Transactional
//	public ArrayList<String> findManagerNameByUID(String userId) {
//		return userRepository.findManagerNameByUID(userId);
//	}
//	
//	@Override
//	@Transactional
//	public User authenticate(String uname, String pwd) {
//		User u = userRepository.findUserByNamePwd(uname, pwd);
//		return u;
//	}
//
//}