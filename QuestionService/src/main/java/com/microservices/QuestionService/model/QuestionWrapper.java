package com.microservices.QuestionService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class QuestionWrapper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String questionTitle;
	private String category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public QuestionWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionWrapper(int id, String questionTitle, String category) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.category = category;
	}
		
}
