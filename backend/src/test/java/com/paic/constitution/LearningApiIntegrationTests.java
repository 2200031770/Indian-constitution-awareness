package com.paic.constitution;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.constitution.entity.QuizQuestion;
import com.paic.constitution.repository.QuizQuestionRepository;

@SpringBootTest
@AutoConfigureMockMvc
class LearningApiIntegrationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private QuizQuestionRepository questions;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void topicsAndQuizQuestionsAreAvailableWithoutLeakingAnswerKeys() throws Exception {
		mockMvc.perform(get("/api/v1/topics"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(6)))
				.andExpect(jsonPath("$[0].slug", is("preamble")));

		mockMvc.perform(get("/api/v1/quiz/questions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(8)))
				.andExpect(jsonPath("$[0].options", hasSize(4)))
				.andExpect(jsonPath("$[0].correctOption").doesNotExist());
	}

	@Test
	void quizSubmissionReturnsScoreAndExplanations() throws Exception {
		QuizQuestion firstQuestion = questions.findAllByOrderByIdAsc().get(0);
		Map<Long, String> answers = Map.of(firstQuestion.getId(), firstQuestion.getCorrectOption());

		mockMvc.perform(post("/api/v1/quiz/submit")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(Map.of("answers", answers))))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.score", is(1)))
				.andExpect(jsonPath("$.total", is(8)))
				.andExpect(jsonPath("$.results", hasSize(8)))
				.andExpect(jsonPath("$.results[0].correct", is(true)))
				.andExpect(jsonPath("$.results[0].explanation").isNotEmpty());
	}

	@Test
	void unknownTopicReturnsNotFound() throws Exception {
		mockMvc.perform(get("/api/v1/topics/not-a-real-topic"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Learning topic not found")));
	}
}
