package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.insurance.dao.PolicyRepository;
import com.insurance.dao.UserRepository;
import com.insurance.entity.Policy;
import com.insurance.entity.User;
import com.insurance.exceptions.PolicyNotFoundException;

@RestController
public class AdminController {

	@Autowired
	PolicyRepository policyRepo;

	@Autowired
	UserRepository userRepo;

	// *****************************************
	// ADMIN
	// *****************************************

	// ADMIN : Create Policy
	@RequestMapping(value = "/admin/policy", method = RequestMethod.POST)
	public Policy createPolicy(@RequestBody Policy policy) {
		return policyRepo.save(policy);
	}

	// ADMIN : Update Policy
	@RequestMapping(value = "/admin/policy/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Policy> updatePolicy(@PathVariable("id") Integer id, @RequestBody Policy policy) {
		Optional<Policy> optP = policyRepo.findById(id);
		if (optP.isEmpty()) {
			throw new PolicyNotFoundException();
		}

		Policy p = new Policy();
		p = optP.get();

		p.setPolicyType(policy.getPolicyType());
		final Policy updatedPolicy = policyRepo.save(p);
		return ResponseEntity.ok(updatedPolicy);

	}

	// ADMIN : List all Policies
	@RequestMapping(value = "/admin/policy", method = RequestMethod.GET)
	public Iterable<Policy> ListPolicy() {
		return policyRepo.findAll();
	}

	// ADMIN : List Policy by ID
	@RequestMapping(value = "/admin/policy/{id}", method = RequestMethod.GET)
	public Policy getPolicyById(@PathVariable("id") Integer id) {
		Optional<Policy> optP = policyRepo.findById(id);
		if(optP.isEmpty()) {
			throw new PolicyNotFoundException();
		}
		return policyRepo.findById(id).get();
	}


	// ADMIN : Delete a Policy
	@RequestMapping(value = "/admin/policy/{id}", method = RequestMethod.DELETE)
	public String deletePolicy(@PathVariable("id") Integer id) {
		Optional<Policy> optP = policyRepo.findById(id);
		if (optP.isEmpty()) {
			throw new PolicyNotFoundException();
		}
		Policy p = new Policy();
		p = optP.get();

		// delete the Policy if not in use
		if (p.getUserId() == 0) {
			policyRepo.deleteById(id);

			return "Policy Deleted.";
		} else {
			return "Policy in use, cannot be deleted.";
		}
	}

}
