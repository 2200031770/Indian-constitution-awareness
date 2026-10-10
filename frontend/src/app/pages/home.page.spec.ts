import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { HomePage } from './home.page';

describe('HomePage', () => {
  let http: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePage],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([])],
    }).compileComponents();
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('renders topics and category filters from the backend response', async () => {
    const fixture = TestBed.createComponent(HomePage);
    fixture.detectChanges();

    const request = http.expectOne('/api/v1/topics');
    expect(request.request.method).toBe('GET');
    request.flush([
      {
        id: 1,
        slug: 'preamble',
        title: 'The Preamble',
        category: 'Foundations',
        summary: 'The opening promise of the Constitution.',
        content: 'Justice, liberty, equality and fraternity.',
        icon: '✳',
        readMinutes: 4,
        featured: true,
      },
      {
        id: 2,
        slug: 'fundamental-rights',
        title: 'Fundamental Rights',
        category: 'Rights & citizenship',
        summary: 'Protections that uphold individual liberty.',
        content: 'Part III.',
        icon: '◈',
        readMinutes: 7,
        featured: true,
      },
    ]);
    await fixture.whenStable();
    fixture.detectChanges();

    const page = fixture.nativeElement as HTMLElement;
    expect(page.querySelectorAll('.topic-card')).toHaveLength(2);
    expect(page.querySelector('.topic-card h3')?.textContent).toContain('The Preamble');
    expect(Array.from(page.querySelectorAll('.filters button')).map((button) => button.textContent?.trim()))
      .toEqual(['All topics', 'Foundations', 'Rights & citizenship']);
  });
});
