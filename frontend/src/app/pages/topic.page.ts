import { HttpErrorResponse } from '@angular/common/http';
import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { LearningApiService } from '../core/learning-api.service';
import { Topic } from '../core/models';

@Component({
  selector: 'app-topic-page',
  imports: [RouterLink],
  templateUrl: './topic.page.html',
  styleUrl: './topic.page.css',
})
export class TopicPage implements OnInit {
  private readonly api = inject(LearningApiService);
  private readonly route = inject(ActivatedRoute);
  readonly topic = signal<Topic | null>(null);
  readonly topics = signal<Topic[]>([]);
  readonly loading = signal(true);
  readonly error = signal('');
  readonly relatedError = signal(false);

  get paragraphs(): string[] {
    return this.topic()?.content.split('\n\n') ?? [];
  }

  ngOnInit(): void {
    this.api.getTopics().subscribe({
      next: (topics) => this.topics.set(topics),
      error: () => this.relatedError.set(true),
    });
    this.route.paramMap.subscribe((params) => {
      const slug = params.get('slug');
      if (!slug) {
        this.error.set('This learning guide could not be found.');
        this.loading.set(false);
        return;
      }
      this.loading.set(true);
      this.error.set('');
      this.api.getTopic(slug).subscribe({
        next: (topic) => {
          this.topic.set(topic);
          this.loading.set(false);
        },
        error: (error: HttpErrorResponse) => {
          this.error.set(error.status === 404
            ? 'We could not find that guide. Browse the library to explore other topics.'
            : 'This guide could not be loaded right now. Please try again.');
          this.loading.set(false);
        },
      });
    });
  }
}
