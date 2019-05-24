//package com.leave.system.repository;
//
//import java.util.ArrayList;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.leave.system.model.User;
//
//public interface UserRepository extends JpaRepository<User, String> {
//	
//	@Query("SELECT DISTINCT e2.name FROM User u, Employee e1, Employee e2 WHERE u.id = e1.id AND e1.id = e2.id AND u.userId=:userid")
//	ArrayList<String> findManagerNameByUID(@Param("uid") String uid);
//    
//	@Query("SELECT u FROM User u WHERE u.name=:userid AND u.password=:password")
//	User findUserByNamePwd(@Param("un") String uname, @Param("pwd") String pwd);
//
//}
