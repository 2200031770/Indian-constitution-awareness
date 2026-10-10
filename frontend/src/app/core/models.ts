export interface Topic {
  id: number;
  slug: string;
  title: string;
  category: string;
  summary: string;
  content: string;
  icon: string;
  readMinutes: number;
  featured: boolean;
}

export interface QuizOption {
  id: string;
  text: string;
}

export interface QuizQuestion {
  id: number;
  topicTitle: string;
  prompt: string;
  options: QuizOption[];
}

export interface QuizQuestionResult {
  questionId: number;
  prompt: string;
  selectedOption: string | null;
  correctOption: string;
  correctAnswer: string;
  explanation: string;
  correct: boolean;
}

export interface QuizResult {
  score: number;
  total: number;
  percentage: number;
  results: QuizQuestionResult[];
}
