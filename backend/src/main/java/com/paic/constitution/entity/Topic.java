package com.paic.constitution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 80)
	private String slug;

	@Column(nullable = false, length = 120)
	private String title;

	@Column(nullable = false, length = 60)
	private String category;

	@Column(nullable = false, length = 300)
	private String summary;

	@Column(nullable = false, length = 4000)
	private String content;

	@Column(nullable = false, length = 8)
	private String icon;

	@Column(nullable = false)
	private int readMinutes;

	@Column(nullable = false)
	private int displayOrder;

	@Column(nullable = false)
	private boolean featured;

	protected Topic() {
	}

	public Topic(String slug, String title, String category, String summary, String content,
			String icon, int readMinutes, int displayOrder, boolean featured) {
		this.slug = slug;
		this.title = title;
		this.category = category;
		this.summary = summary;
		this.content = content;
		this.icon = icon;
		this.readMinutes = readMinutes;
		this.displayOrder = displayOrder;
		this.featured = featured;
	}

	public Long getId() { return id; }
	public String getSlug() { return slug; }
	public String getTitle() { return title; }
	public String getCategory() { return category; }
	public String getSummary() { return summary; }
	public String getContent() { return content; }
	public String getIcon() { return icon; }
	public int getReadMinutes() { return readMinutes; }
	public int getDisplayOrder() { return displayOrder; }
	public boolean isFeatured() { return featured; }
}
