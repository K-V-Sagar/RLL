package com.insurance.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dao.PolicyRepository;
import com.insurance.dao.UserRepository;
import com.insurance.entity.Policy;
import com.insurance.entity.User;
import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.exceptions.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	PolicyRepository policyRepo;

	@Autowired
	UserRepository userRepo;

	// *****************************************
	// USER
	// *****************************************

	/*
	 * 0 - Initial state (when user has not applied for a policy) 1 - Approved state
	 * (After the Admin approves the policy request) 2 - Disapproved state (After
	 * the Admin rejects the request) 3 - Pending state (Once the user applies for a
	 * policy, PolicyState changes from 0 to 3)
	 */

	// USER : Apply Policy
	@RequestMapping(value = "/user/{uid}/policy/{pid}", method = RequestMethod.PUT)
	public ResponseEntity<Policy> applyPolicy(@PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid,
			@RequestBody Policy policy) {

		// checking if policy exists
		Optional<Policy> optP = policyRepo.findById(pid);
		if (optP.isEmpty()) {
			throw new PolicyNotFoundException();
		}

		// checking if user exists
		Optional<User> optU = userRepo.findById(uid);
		if (optU.isEmpty()) {
			throw new UserNotFoundException();
		}

		Policy p = new Policy();
		p = optP.get();

		User u = new User();
		u = optU.get();

		// adding policy number to the user table
		u.setPolicyNum(policy.getPolicyNum());
		userRepo.save(u);

		// adding user id to the policy table
		p.setUserId(uid);
		p.setApproval(3);
		final Policy updatedPolicy = policyRepo.save(p);
		return ResponseEntity.ok(updatedPolicy);

	}

}
