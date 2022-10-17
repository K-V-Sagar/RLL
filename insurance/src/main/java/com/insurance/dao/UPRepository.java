package com.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.UserPolicy;

@Repository
public interface UPRepository extends JpaRepository<UserPolicy, Integer>{

	
	
}