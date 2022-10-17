package com.insurance.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dao.QuestionRepository;
import com.insurance.entity.Question;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepo;
	
	// user can ask query
	@PostMapping("/user/question")
	public Question askQuestion(@RequestBody Question question) {
		return questionRepo.save(question);
		
	}
	
	// Admin can list all queries
	@GetMapping("/admin/question")
	public List<Question> listQuestions(){
		return questionRepo.findAll();
		
	}
	
	// Admin Answer 
	@PutMapping("/admin/question/{qid}")
	public ResponseEntity<Question> answerQuestion(@PathVariable int qid, @RequestBody Question question) {
		Optional<Question> optQ = questionRepo.findById(qid);
		
		Question q = new Question();
		q = optQ.get();
		
		q.setAnswer(question.getAnswer());
		final Question updatedQuestion = questionRepo.save(q);
		return ResponseEntity.ok(updatedQuestion);
		
	}
	
	

}