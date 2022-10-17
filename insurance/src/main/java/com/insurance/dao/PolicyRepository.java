package com.insurance.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.insurance.entity.Policy;
import com.insurance.entity.UserPolicy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

//	UserPolicy findAllById(int userId);

	@Query(value = "SELECT * FROM Policy WHERE user_id = ?1", nativeQuery = true)
    public List<Policy> findAllById(int uid);
	
}