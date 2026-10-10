import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  selector: 'app-root',
  styleUrl: './app.css',
  template: `
    <header class="site-header">
      <a class="brand" routerLink="/" aria-label="Samvidhan home">
        <span class="brand-mark" aria-hidden="true">S</span>
        <span class="brand-copy"><strong>Samvidhan</strong><small>THE PEOPLE'S CONSTITUTION</small></span>
      </a>
      <nav aria-label="Main navigation">
        <a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{ exact: true }">Explore</a>
        <a routerLink="/quiz" routerLinkActive="active">Knowledge check</a>
      </nav>
      <a class="header-cta" routerLink="/quiz">Take a quiz <span aria-hidden="true">↗</span></a>
    </header>
    <main><router-outlet /></main>
    <footer class="site-footer">
      <a class="footer-brand" routerLink="/">Samvidhan</a>
      <p>A clearer understanding of the Constitution that belongs to us all.</p>
      <span>For learning and awareness · Not legal advice</span>
    </footer>
  `,
})
export class App {}
