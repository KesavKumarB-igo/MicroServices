package com.microservices.QuizService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.QuizService.dao.QuizDao;
import com.microservices.QuizService.feign.QuizInterface;
import com.microservices.QuizService.model.QuestionWrapper;
import com.microservices.QuizService.model.Quiz;
import com.microservices.QuizService.model.Response;


@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(questions);
			quizDao.save(quiz);
			
			return new ResponseEntity<>("Successfully Created Quiz",HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Error while Creating Quiz", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		try {
			Quiz quiz = quizDao.findById(id).get();
			List<Integer> questionIds = quiz.getQuestionIds();
			
			ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
			
			return (questions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Integer> calculateTotalResult(Integer id, List<Response> responses) {
		try {
			ResponseEntity<Integer> score = quizInterface.getScore(responses);
			return score;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
	}
}
