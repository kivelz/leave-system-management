package com.leave.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.system.model.Leavedetail;

@Repository
public interface LeaveRepository extends JpaRepository<Leavedetail, Integer>{

}
