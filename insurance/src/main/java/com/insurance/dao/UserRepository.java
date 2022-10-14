package com.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}