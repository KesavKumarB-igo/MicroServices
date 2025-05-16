package com.microservices.QuestionService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.QuestionService.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(@Param("category")String category);
	
	@Query(value = "Select q.id from Question q where q.category= :category order by rand() limit :numQ", nativeQuery = true)
	List<Integer> createQuizByCategoryAndNumQ(@Param("category") String category, @Param("numQ") int numQ);
}
