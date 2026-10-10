import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { LearningApiService } from './learning-api.service';

describe('LearningApiService', () => {
  let api: LearningApiService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    api = TestBed.inject(LearningApiService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('loads learning topics from the versioned backend API', () => {
    api.getTopics().subscribe((topics) => expect(topics).toHaveLength(1));

    const request = http.expectOne('/api/v1/topics');
    expect(request.request.method).toBe('GET');
    request.flush([{
      id: 1,
      slug: 'preamble',
      title: 'The Preamble',
      category: 'Foundations',
      summary: 'The opening promise of the Constitution.',
      content: 'Justice, liberty, equality and fraternity.',
      icon: '✳',
      readMinutes: 4,
      featured: true,
    }]);
  });

  it('submits quiz answers to the backend for server-side scoring', () => {
    const answers = { 1: 'A', 2: 'C' };
    api.submitQuiz(answers).subscribe();

    const request = http.expectOne('/api/v1/quiz/submit');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual({ answers });
    request.flush({ score: 1, total: 2, percentage: 50, results: [] });
  });
});
