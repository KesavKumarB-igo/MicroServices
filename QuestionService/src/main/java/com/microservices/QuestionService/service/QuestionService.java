package com.microservices.QuestionService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.QuestionService.dao.QuestionDao;
import com.microservices.QuestionService.model.Question;
import com.microservices.QuestionService.model.QuestionWrapper;
import com.microservices.QuestionService.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> allQuestions() {
		try {
			if(questionDao.count() == 0)
			{
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Object> deleteQuestion(int id) {
		try {
			if (questionDao.existsById(id)) {
				questionDao.deleteById(id);
				return new ResponseEntity<>(HttpStatus.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Object> updateQuestion(int id, Question newQuestion) {
		try {
			Optional<Question> isQuestion = questionDao.findById(newQuestion.getId());
			if (isQuestion.isPresent()) {
				Question question = isQuestion.get();
				question.setQuestionTitle(newQuestion.getQuestionTitle());
				question.setCategory(newQuestion.getCategory());
				question.setDifficultyLevel(newQuestion.getDifficultyLevel());
				question.setRightAnswer(newQuestion.getRightAnswer());
				;
				questionDao.save(question);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		try {
			List<Integer> questions = questionDao.createQuizByCategoryAndNumQ(categoryName, numQuestions);
			
			return new ResponseEntity<>(questions, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
		try {
			List<QuestionWrapper> wrappers = new ArrayList<>();
			List<Question> questions = new ArrayList<>();
			
			for(Integer id: questionIds) {
				questions.add(questionDao.findById(id).get());
			}
			for(Question question: questions) {
				QuestionWrapper wrapper = new QuestionWrapper();
				wrapper.setId(question.getId());
				wrapper.setQuestionTitle(question.getQuestionTitle());
				wrapper.setCategory(question.getCategory());
				wrappers.add(wrapper);
			}
			return new ResponseEntity<>(wrappers, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateScore(List<Response> responses) {
		try {
			int correct = 0;
			for(Response response : responses) {
				Question question = questionDao.findById(response.getId()).get();
				if(response.getResponse().equals(question.getRightAnswer())) {
					correct++;
				}
			}
			return new ResponseEntity<>(correct, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
	}
	
}
