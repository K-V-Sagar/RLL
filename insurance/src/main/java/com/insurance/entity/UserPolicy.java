package com.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userpolicy")
public class UserPolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int upId;
	
	private int userId;
	private String policyNum;
	public UserPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPolicy(int upId, int userId, String policyNum) {
		super();
		this.upId = upId;
		this.userId = userId;
		this.policyNum = policyNum;
	}
	public int getUpId() {
		return upId;
	}
	public void setUpId(int upId) {
		this.upId = upId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
	@Override
	public String toString() {
		return "UserPolicy [upId=" + upId + ", userId=" + userId + ", policyNum=" + policyNum + "]";
	}
	
	

}