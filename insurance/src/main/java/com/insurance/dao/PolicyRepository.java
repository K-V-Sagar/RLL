package com.insurance.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

	
	
}