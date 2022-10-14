package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dao.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
}
