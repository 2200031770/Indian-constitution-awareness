package com.paic.constitution.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paic.constitution.dto.QuizQuestionResponse;
import com.paic.constitution.dto.QuizResult;
import com.paic.constitution.dto.QuizSubmission;
import com.paic.constitution.service.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
	private final QuizService quizService;

	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}

	@GetMapping("/questions")
	public List<QuizQuestionResponse> questions() {
		return quizService.getQuestions();
	}

	@PostMapping("/submit")
	public QuizResult submit(@Valid @RequestBody QuizSubmission submission) {
		return quizService.submit(submission.answers());
	}
}
