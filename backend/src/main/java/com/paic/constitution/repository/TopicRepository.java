package com.paic.constitution.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paic.constitution.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
	List<Topic> findAllByOrderByDisplayOrderAsc();
	Optional<Topic> findBySlug(String slug);
}
