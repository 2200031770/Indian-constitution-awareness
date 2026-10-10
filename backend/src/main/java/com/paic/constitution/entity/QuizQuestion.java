package com.paic.constitution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "topic_id", nullable = false)
	private Topic topic;

	@Column(nullable = false, length = 500)
	private String prompt;

	@Column(nullable = false, length = 200)
	private String optionA;

	@Column(nullable = false, length = 200)
	private String optionB;

	@Column(nullable = false, length = 200)
	private String optionC;

	@Column(nullable = false, length = 200)
	private String optionD;

	@Column(nullable = false, length = 1)
	private String correctOption;

	@Column(nullable = false, length = 500)
	private String explanation;

	protected QuizQuestion() {
	}

	public QuizQuestion(Topic topic, String prompt, String optionA, String optionB,
			String optionC, String optionD, String correctOption, String explanation) {
		this.topic = topic;
		this.prompt = prompt;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctOption = correctOption;
		this.explanation = explanation;
	}

	public Long getId() { return id; }
	public Topic getTopic() { return topic; }
	public String getPrompt() { return prompt; }
	public String getOptionA() { return optionA; }
	public String getOptionB() { return optionB; }
	public String getOptionC() { return optionC; }
	public String getOptionD() { return optionD; }
	public String getCorrectOption() { return correctOption; }
	public String getExplanation() { return explanation; }
}
