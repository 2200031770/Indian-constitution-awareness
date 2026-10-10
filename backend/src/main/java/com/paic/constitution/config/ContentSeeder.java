package com.paic.constitution.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.paic.constitution.entity.QuizQuestion;
import com.paic.constitution.entity.Topic;
import com.paic.constitution.repository.QuizQuestionRepository;
import com.paic.constitution.repository.TopicRepository;

@Configuration
public class ContentSeeder {
	@Bean
	CommandLineRunner seedLearningContent(TopicRepository topics, QuizQuestionRepository questions) {
		return args -> seed(topics, questions);
	}

	@Transactional
	void seed(TopicRepository topics, QuizQuestionRepository questions) {
		if (topics.count() != 0) {
			return;
		}

		List<Topic> content = topics.saveAll(List.of(
				new Topic("preamble", "The Preamble", "Foundations",
						"The opening promise of the Constitution: justice, liberty, equality and fraternity.",
						"The Preamble introduces the Constitution and expresses its guiding values. It describes India as a sovereign, socialist, secular, democratic republic and sets out the aspiration to secure justice, liberty, equality and fraternity for all citizens.\n\nIt is a key to understanding the spirit of the Constitution. The Supreme Court has recognised that the Preamble is part of the Constitution, while it does not by itself grant an independent source of power.",
						"✳", 4, 1, true),
				new Topic("fundamental-rights", "Fundamental Rights", "Rights & citizenship",
						"Protections that uphold individual liberty, equality and dignity.",
						"Part III of the Constitution (Articles 12–35) sets out Fundamental Rights. They protect people against certain forms of arbitrary state action and help make equality, liberty and dignity meaningful.\n\nThe rights include equality before law, freedoms such as speech and expression, protection against exploitation, freedom of religion, cultural and educational rights, and the right to constitutional remedies. These rights are not absolute: the Constitution permits reasonable restrictions in specified circumstances. Article 32 allows people to approach the Supreme Court to enforce Fundamental Rights.",
						"◈", 7, 2, true),
				new Topic("directive-principles", "Directive Principles", "Governance",
						"Constitutional goals that guide the state towards social and economic well-being.",
						"Part IV (Articles 36–51) contains the Directive Principles of State Policy. They set out social and economic objectives for governments, including public health, education, just working conditions and reducing inequalities.\n\nUnder Article 37, these principles are not enforceable by courts, but they are fundamental in the governance of the country. They guide lawmaking and public policy, and are intended to be applied by the state when making laws.",
						"◎", 5, 3, false),
				new Topic("fundamental-duties", "Fundamental Duties", "Rights & citizenship",
						"Eleven civic responsibilities in Part IVA that encourage respect and participation.",
						"Article 51A in Part IVA lists the Fundamental Duties of citizens. They include respecting the Constitution, the National Flag and the National Anthem; cherishing the ideals of the freedom struggle; protecting the country's unity; and promoting harmony.\n\nOther duties include protecting the environment, developing scientific temper, safeguarding public property, striving for excellence and providing opportunities for education to children between six and fourteen years. The duties were introduced by the 42nd Amendment in 1976; the education-related duty was added by the 86th Amendment in 2002.",
						"◉", 5, 4, false),
				new Topic("parliament", "Parliament & Government", "Institutions",
						"How India's Union government makes laws and is accountable to the people.",
						"At the Union level, Parliament consists of the President and two Houses: the Rajya Sabha (Council of States) and the Lok Sabha (House of the People). Parliament makes laws on subjects assigned to the Union and Concurrent Lists, scrutinises government, and approves public expenditure.\n\nIndia follows a parliamentary system. The Council of Ministers, headed by the Prime Minister, is collectively responsible to the Lok Sabha. The President is the constitutional head of the Union and performs functions laid down in the Constitution.",
						"▤", 6, 5, false),
				new Topic("federalism", "Federalism & States", "Institutions",
						"Powers are divided between the Union and states, with constitutional checks and cooperation.",
						"The Constitution distributes legislative subjects between Parliament and state legislatures through the Union, State and Concurrent Lists in the Seventh Schedule. The Union and states each have their own governments and responsibilities.\n\nIndia's federal design also provides for cooperation and coordination between levels of government. The Constitution includes mechanisms for handling overlaps and exceptional situations; the balance of responsibilities should be understood by reading its provisions as a whole.",
						"⌘", 6, 6, false)));

		questions.saveAll(List.of(
				new QuizQuestion(content.get(0), "Which values are expressly named in the Preamble?",
						"Justice, liberty, equality and fraternity", "Wealth, security, peace and order",
						"Faith, language, culture and trade", "Unity, industry, education and progress",
						"A", "The Preamble resolves to secure justice, liberty, equality and fraternity."),
				new QuizQuestion(content.get(0), "The Preamble describes India as a:",
						"Confederation", "Sovereign, socialist, secular, democratic republic",
						"Constitutional monarchy", "Presidential federation",
						"B", "These words describe the Republic in the Preamble."),
				new QuizQuestion(content.get(1), "Fundamental Rights are primarily set out in which part?",
						"Part II", "Part III", "Part IV", "Part IVA",
						"B", "Part III of the Constitution contains Fundamental Rights."),
				new QuizQuestion(content.get(1), "Which Article provides the right to move the Supreme Court to enforce Fundamental Rights?",
						"Article 14", "Article 19", "Article 32", "Article 51A",
						"C", "Article 32 provides the right to constitutional remedies in the Supreme Court."),
				new QuizQuestion(content.get(2), "What is the constitutional status of the Directive Principles?",
						"All are directly enforceable by courts",
						"They are not court-enforceable but are fundamental in governance",
						"They apply only during emergencies",
						"They bind citizens but not the state",
						"B", "Article 37 says the principles are not enforceable by courts but are fundamental in governance."),
				new QuizQuestion(content.get(3), "Where are the Fundamental Duties listed?",
						"Article 32", "Article 44", "Article 51A", "Article 368",
						"C", "Article 51A in Part IVA lists the Fundamental Duties."),
				new QuizQuestion(content.get(4), "Which two Houses make up India's Parliament?",
						"Lok Sabha and Legislative Assembly", "Rajya Sabha and Lok Sabha",
						"Rajya Sabha and Gram Sabha", "Lok Sabha and Supreme Court",
						"B", "Parliament consists of the President, Rajya Sabha and Lok Sabha."),
				new QuizQuestion(content.get(5), "Which schedule distributes legislative subjects among Union, State and Concurrent Lists?",
						"Fifth Schedule", "Seventh Schedule", "Ninth Schedule", "Tenth Schedule",
						"B", "The Seventh Schedule contains the three legislative lists.")));
	}
}
