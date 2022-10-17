package com.insurance.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dao.PolicyRepository;
import com.insurance.dao.UPRepository;
import com.insurance.dao.UserRepository;
import com.insurance.entity.Policy;
import com.insurance.entity.User;
import com.insurance.entity.UserPolicy;
import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.exceptions.UserNotFoundException;
import com.insurance.service.UserService;

@RestController
public class UserController {

	@Autowired
	PolicyRepository policyRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	UPRepository upRepo;

	@Autowired
	private UserService service;

	// *****************************************
	// USER
	// *****************************************

	/*
	 * 0 - Initial state (when user has not applied for a policy) 1 - Approved state
	 * (After the Admin approves the policy request) 2 - Disapproved state (After
	 * the Admin rejects the request) 3 - Pending state (Once the user applies for a
	 * policy, PolicyState changes from 0 to 3)
	 */

	// create user done by Admin only
	// add user by user but policynum as null

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return service.addUser(user);

	}

	// read All user details by Admin only

	// read by id
	@GetMapping("/user/{id}")
	public User findUserById(@PathVariable int id) {
		return service.getUserById(id);

	}

	// update details by user
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);

	}

	// delete by user
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		return service.deleteUser(id);

	}

	// Admin can see the whole list of users//

	// user login part through userId and password
	@PostMapping("/user/login")
	public String userLogin(@RequestBody User user) {
		return service.userLogin(user);

	}

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

	// USER: View User History (view all policies of a user)
	@RequestMapping(value = "/user/history/{uid}", method = RequestMethod.GET)
	public Iterator<Policy> userHistory(@PathVariable("uid") Integer uid) {

		// user gives userId as input
		List<Policy> up = policyRepo.findAllById(uid);

		return up.iterator();

	}

}
