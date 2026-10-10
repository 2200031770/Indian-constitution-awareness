import { HttpErrorResponse } from '@angular/common/http';
import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LearningApiService } from '../core/learning-api.service';
import { Topic } from '../core/models';

@Component({
  selector: 'app-home-page',
  imports: [RouterLink],
  templateUrl: './home.page.html',
  styleUrl: './home.page.css',
})
export class HomePage implements OnInit {
  private readonly api = inject(LearningApiService);
  readonly topics = signal<Topic[]>([]);
  readonly loading = signal(true);
  readonly error = signal('');
  readonly search = signal('');
  readonly category = signal('All topics');

  get categories(): string[] {
    return ['All topics', ...new Set(this.topics().map((topic) => topic.category))];
  }

  get visibleTopics(): Topic[] {
    const term = this.search().trim().toLowerCase();
    return this.topics().filter((topic) => {
      const matchesCategory = this.category() === 'All topics' || topic.category === this.category();
      const matchesTerm = !term || `${topic.title} ${topic.summary} ${topic.category}`.toLowerCase().includes(term);
      return matchesCategory && matchesTerm;
    });
  }

  ngOnInit(): void {
    this.loadTopics();
  }

  loadTopics(): void {
    this.loading.set(true);
    this.error.set('');
    this.api.getTopics().subscribe({
      next: (topics) => {
        this.topics.set(topics);
        this.loading.set(false);
      },
      error: (error: HttpErrorResponse) => {
        this.error.set(error.status === 0
          ? 'We could not connect to the learning library. Make sure the backend is running and try again.'
          : 'The learning library could not be loaded right now. Please try again.');
        this.loading.set(false);
      },
    });
  }

  setSearch(value: string): void {
    this.search.set(value);
  }

  setCategory(category: string): void {
    this.category.set(category);
  }
}
