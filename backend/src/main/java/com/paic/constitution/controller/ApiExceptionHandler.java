package com.paic.constitution.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paic.constitution.service.ResourceNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> notFound(ResourceNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiError(Instant.now(), 404, "Not found", exception.getMessage()));
	}

	@ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<ApiError> badRequest(Exception exception) {
		String message = exception instanceof MethodArgumentNotValidException
				? "Please provide valid quiz answers"
				: exception.getMessage();
		return ResponseEntity.badRequest()
				.body(new ApiError(Instant.now(), 400, "Bad request", message));
	}

	public record ApiError(Instant timestamp, int status, String error, String message) {}
}
