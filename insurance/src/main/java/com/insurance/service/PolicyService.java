package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dao.PolicyRepository;

@Service
public class PolicyService {
	@Autowired
	PolicyRepository policyRepo;
	
	
}