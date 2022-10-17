package com.insurance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	

}