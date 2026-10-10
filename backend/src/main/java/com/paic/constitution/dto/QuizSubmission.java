package com.paic.constitution.dto;

import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record QuizSubmission(
		@NotEmpty Map<@NotNull Long, @Pattern(regexp = "(?i)[A-D]") String> answers) {
}
