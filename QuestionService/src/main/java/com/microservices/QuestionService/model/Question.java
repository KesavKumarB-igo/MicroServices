package com.microservices.QuestionService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String questionTitle;
	private String category;
	private String difficultyLevel;
	private String rightAnswer;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Question(Integer id, String questionTitle, String category, String difficultyLevel, String rightAnswer) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.category = category;
		this.difficultyLevel = difficultyLevel;
		this.rightAnswer = rightAnswer;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionTitle=" + questionTitle + ", category=" + category
				+ ", difficultyLevel=" + difficultyLevel + ", rightAnswer=" + rightAnswer + "]";
	}
	
	
	
}
