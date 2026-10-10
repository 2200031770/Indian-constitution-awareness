import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { TestBed } from '@angular/core/testing';
import { App } from './app';

describe('App', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
      providers: [provideHttpClient(), provideRouter([])],
    }).compileComponents();
  });

  it('renders the Samvidhan brand and primary navigation', () => {
    const fixture = TestBed.createComponent(App);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;

    expect(compiled.querySelector('.brand-copy strong')?.textContent).toContain('Samvidhan');
    expect(compiled.querySelector('nav')?.textContent).toContain('Explore');
    expect(compiled.querySelector('nav')?.textContent).toContain('Knowledge check');
  });
});
