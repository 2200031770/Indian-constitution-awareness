package com.paic.constitution.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paic.constitution.dto.QuizQuestionResponse;
import com.paic.constitution.dto.QuizResult;
import com.paic.constitution.entity.QuizQuestion;
import com.paic.constitution.repository.QuizQuestionRepository;

@Service
public class QuizService {
	private final QuizQuestionRepository questionRepository;

	public QuizService(QuizQuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@Transactional(readOnly = true)
	public List<QuizQuestionResponse> getQuestions() {
		return questionRepository.findAllByOrderByIdAsc().stream()
				.map(QuizQuestionResponse::from)
				.toList();
	}

	@Transactional(readOnly = true)
	public QuizResult submit(Map<Long, String> answers) {
		List<QuizQuestion> questions = questionRepository.findAllByOrderByIdAsc();
		Set<Long> questionIds = questions.stream().map(QuizQuestion::getId).collect(Collectors.toSet());
		if (!questionIds.containsAll(answers.keySet())) {
			throw new IllegalArgumentException("Submission contains an unknown question");
		}

		List<QuizResult.QuestionResult> results = questions.stream().map(question -> {
			String selected = answers.get(question.getId());
			if (selected != null) {
				selected = selected.toUpperCase(Locale.ROOT);
			}
			String correctOption = question.getCorrectOption();
			boolean correct = correctOption.equals(selected);
			return new QuizResult.QuestionResult(question.getId(), question.getPrompt(), selected,
					correctOption, answerText(question, correctOption), question.getExplanation(), correct);
		}).toList();

		int score = (int) results.stream().filter(QuizResult.QuestionResult::correct).count();
		int total = results.size();
		int percentage = total == 0 ? 0 : Math.round(score * 100f / total);
		return new QuizResult(score, total, percentage, results);
	}

	private String answerText(QuizQuestion question, String option) {
		return switch (option) {
			case "A" -> question.getOptionA();
			case "B" -> question.getOptionB();
			case "C" -> question.getOptionC();
			case "D" -> question.getOptionD();
			default -> throw new IllegalStateException("Question has an invalid answer key");
		};
	}
}
