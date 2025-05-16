package com.microservices.QuizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.QuizService.model.QuestionWrapper;
import com.microservices.QuizService.model.Response;

@FeignClient(name= "QUESTIONSERVICE")
public interface QuizInterface {

	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam("categoryName") String categoryName, @RequestParam("numQuestions") Integer numQuestions);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	
}
