package com.insurance.exceptions;

public class PolicyNotFoundException extends RuntimeException {
	public PolicyNotFoundException() {
		super("Policy Not Found.");
	}
}