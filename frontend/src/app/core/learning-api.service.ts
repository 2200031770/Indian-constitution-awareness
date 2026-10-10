import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuizQuestion, QuizResult, Topic } from './models';

@Injectable({ providedIn: 'root' })
export class LearningApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = '/api/v1';

  getTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.baseUrl}/topics`);
  }

  getTopic(slug: string): Observable<Topic> {
    return this.http.get<Topic>(`${this.baseUrl}/topics/${encodeURIComponent(slug)}`);
  }

  getQuizQuestions(): Observable<QuizQuestion[]> {
    return this.http.get<QuizQuestion[]>(`${this.baseUrl}/quiz/questions`);
  }

  submitQuiz(answers: Record<number, string>): Observable<QuizResult> {
    return this.http.post<QuizResult>(`${this.baseUrl}/quiz/submit`, { answers });
  }
}
