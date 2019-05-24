//package com.leave.system.service;
//
//import java.util.ArrayList;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import com.leave.system.model.Role;
//import com.leave.system.model.User;
//
//public interface UserService {
//
//	ArrayList<User> findAllUsers();
//
//	User findUser(String userId);
//
//	User createUser(User user);
//
//	User changeUser(User user);
//
//	void removeUser(User user);
//
//	ArrayList<Role> findRolesForUser(String userId);
//
//	ArrayList<String> findRoleNamesForUser(String userId);
//
//	ArrayList<String> findManagerNameByUID(String userId);
//
//	User authenticate(String uname, String pwd);
//
//}