package com.insurance.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.dao.UserRepository;
import com.insurance.entity.Policy;
import com.insurance.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	// create by user only but keep policynum null at this time
	public User addUser(User user) {
		return userRepo.save(user);
	}
	
	
	
	// read
	public User getUserById(int id){
		return userRepo.findById(id).orElse(null);
		
	}
	
	
	//  delete if a user want to complete delete their existence
	public String deleteUser(int id) {
		userRepo.deleteById(id);
		return "User Deleted: "+id ;
		
	}
	
	// update user through user, but only sum parameters
	
	public User updateUser(User user) {
		
		User existinguser = userRepo.findById(user.getUserId()).orElse(null);
		existinguser.setAddress(user.getAddress());
		existinguser.setName(user.getName());
		existinguser.setPassword(user.getPassword());
		existinguser.setPhoneNum(user.getPhoneNum());
		// doubt existinguser.setEmail(user.getEmail());
		//existinguser.setPolicyNum(null);
		return userRepo.save(existinguser);
		
		
		
	}
	
	// user login function implementation using id and password
	public String userLogin(User user) {
		
		User existingdetails =userRepo.findByEmail(user.getEmail()).orElse(null);
		System.out.println(existingdetails.getPassword());
		System.out.println(user.getPassword());
		String a = existingdetails.getPassword();
		String b = user.getPassword();
		
		String x = existingdetails.getEmail();
		String y = user.getEmail();
		
		if((x.contentEquals(y)) && a.contentEquals(b)) {
			
			return "User Login Successful";
		}
		else{
			
			return "Invalid User Credentials";
			
		}
		
		
		
		
		
	}



	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}