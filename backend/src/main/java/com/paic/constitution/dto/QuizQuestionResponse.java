package com.paic.constitution.dto;

import java.util.List;

import com.paic.constitution.entity.QuizQuestion;

public record QuizQuestionResponse(
		Long id,
		String topicTitle,
		String prompt,
		List<AnswerOption> options) {

	public static QuizQuestionResponse from(QuizQuestion question) {
		return new QuizQuestionResponse(question.getId(), question.getTopic().getTitle(),
				question.getPrompt(), List.of(
						new AnswerOption("A", question.getOptionA()),
						new AnswerOption("B", question.getOptionB()),
						new AnswerOption("C", question.getOptionC()),
						new AnswerOption("D", question.getOptionD())));
	}

	public record AnswerOption(String id, String text) {}
}
