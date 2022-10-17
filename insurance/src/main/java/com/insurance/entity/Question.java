package com.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="query")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int queryId;
	
	private int userId;
	private String query;
	private String answer;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int queryId, int userId, String query, String answer) {
		super();
		this.queryId = queryId;
		this.userId = userId;
		this.query = query;
		this.answer = answer;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [queryId=" + queryId + ", userId=" + userId + ", query=" + query + ", answer=" + answer + "]";
	}
	
	
	

}