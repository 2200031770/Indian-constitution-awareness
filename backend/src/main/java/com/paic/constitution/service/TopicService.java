package com.paic.constitution.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paic.constitution.dto.TopicResponse;
import com.paic.constitution.entity.Topic;
import com.paic.constitution.repository.TopicRepository;

@Service
public class TopicService {
	private final TopicRepository topicRepository;

	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	@Transactional(readOnly = true)
	public List<TopicResponse> findAll() {
		return topicRepository.findAllByOrderByDisplayOrderAsc().stream()
				.map(TopicResponse::from)
				.toList();
	}

	@Transactional(readOnly = true)
	public TopicResponse findBySlug(String slug) {
		Topic topic = topicRepository.findBySlug(slug)
				.orElseThrow(() -> new ResourceNotFoundException("Learning topic not found"));
		return TopicResponse.from(topic);
	}
}
