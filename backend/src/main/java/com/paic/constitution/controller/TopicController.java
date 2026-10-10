package com.paic.constitution.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paic.constitution.dto.TopicResponse;
import com.paic.constitution.service.TopicService;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {
	private final TopicService topicService;

	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public List<TopicResponse> findAll() {
		return topicService.findAll();
	}

	@GetMapping("/{slug}")
	public TopicResponse findBySlug(@PathVariable String slug) {
		return topicService.findBySlug(slug);
	}
}
