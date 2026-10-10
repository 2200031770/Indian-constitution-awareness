package com.paic.constitution.dto;

import java.util.List;

public record QuizResult(int score, int total, int percentage, List<QuestionResult> results) {
	public record QuestionResult(
			Long questionId,
			String prompt,
			String selectedOption,
			String correctOption,
			String correctAnswer,
			String explanation,
			boolean correct) {}
}
