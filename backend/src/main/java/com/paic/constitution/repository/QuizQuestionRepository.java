package com.paic.constitution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paic.constitution.entity.QuizQuestion;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
	List<QuizQuestion> findAllByOrderByIdAsc();
}
