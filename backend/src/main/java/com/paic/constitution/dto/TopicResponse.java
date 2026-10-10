package com.paic.constitution.dto;

import com.paic.constitution.entity.Topic;

public record TopicResponse(
		Long id,
		String slug,
		String title,
		String category,
		String summary,
		String content,
		String icon,
		int readMinutes,
		boolean featured) {

	public static TopicResponse from(Topic topic) {
		return new TopicResponse(topic.getId(), topic.getSlug(), topic.getTitle(),
				topic.getCategory(), topic.getSummary(), topic.getContent(), topic.getIcon(),
				topic.getReadMinutes(), topic.isFeatured());
	}
}
