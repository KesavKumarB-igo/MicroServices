package com.microservices.QuestionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.QuestionService.model.Question;
import com.microservices.QuestionService.model.QuestionWrapper;
import com.microservices.QuestionService.model.Response;
import com.microservices.QuestionService.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.allQuestions();
	}
	
	@PostMapping("addQuestion") 
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("deleteQuestion/{id}")
	public ResponseEntity<Object> deleteQuestion(@PathVariable("id") int id) {
		return questionService.deleteQuestion(id);
	}
	
	@PutMapping("updateQuestion/{id}")
	public ResponseEntity<Object> updateQuestion(@PathVariable("id") int id, @RequestBody Question newQuestion) {
		return questionService.updateQuestion(id, newQuestion);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByQuestionTitle(@PathVariable("category") String category) {
		return questionService.getByCategory(category);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionsFromIds(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.calculateScore(responses);
	}
	
}
