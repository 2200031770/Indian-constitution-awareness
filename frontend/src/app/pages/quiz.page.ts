import { HttpErrorResponse } from '@angular/common/http';
import { Component, inject, OnInit, signal } from '@angular/core';
import { LearningApiService } from '../core/learning-api.service';
import { QuizQuestion, QuizResult } from '../core/models';

@Component({
  selector: 'app-quiz-page',
  templateUrl: './quiz.page.html',
  styleUrl: './quiz.page.css',
})
export class QuizPage implements OnInit {
  private readonly api = inject(LearningApiService);
  readonly questions = signal<QuizQuestion[]>([]);
  readonly answers = signal<Record<number, string>>({});
  readonly result = signal<QuizResult | null>(null);
  readonly currentIndex = signal(0);
  readonly loading = signal(true);
  readonly submitting = signal(false);
  readonly error = signal('');

  get currentQuestion(): QuizQuestion | null {
    return this.questions()[this.currentIndex()] ?? null;
  }

  get selectedOption(): string {
    const question = this.currentQuestion;
    return question ? this.answers()[question.id] ?? '' : '';
  }

  ngOnInit(): void {
    this.loadQuestions();
  }

  loadQuestions(): void {
    this.loading.set(true);
    this.error.set('');
    this.api.getQuizQuestions().subscribe({
      next: (questions) => {
        this.questions.set(questions);
        this.loading.set(false);
      },
      error: (error: HttpErrorResponse) => {
        this.error.set(error.status === 0
          ? 'We could not connect to the quiz. Make sure the backend is running and try again.'
          : 'The quiz could not be loaded right now. Please try again.');
        this.loading.set(false);
      },
    });
  }

  selectOption(option: string): void {
    const question = this.currentQuestion;
    if (question) {
      this.answers.update((answers) => ({ ...answers, [question.id]: option }));
    }
  }

  next(): void {
    if (!this.selectedOption) return;
    if (this.currentIndex() < this.questions().length - 1) {
      this.currentIndex.update((index) => index + 1);
      return;
    }
    this.submit();
  }

  previous(): void {
    if (this.currentIndex() > 0) {
      this.currentIndex.update((index) => index - 1);
    }
  }

  submit(): void {
    this.submitting.set(true);
    this.error.set('');
    this.api.submitQuiz(this.answers()).subscribe({
      next: (result) => {
        this.result.set(result);
        this.submitting.set(false);
      },
      error: () => {
        this.error.set('Your answers could not be submitted. Please try again.');
        this.submitting.set(false);
      },
    });
  }

  restart(): void {
    this.result.set(null);
    this.answers.set({});
    this.currentIndex.set(0);
    this.error.set('');
  }
}
