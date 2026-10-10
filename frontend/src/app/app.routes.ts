import { Routes } from '@angular/router';
import { HomePage } from './pages/home.page';
import { TopicPage } from './pages/topic.page';
import { QuizPage } from './pages/quiz.page';

export const routes: Routes = [
  { path: '', component: HomePage, title: 'Explore | Samvidhan' },
  { path: 'learn/:slug', component: TopicPage, title: 'Learn | Samvidhan' },
  { path: 'quiz', component: QuizPage, title: 'Knowledge check | Samvidhan' },
  { path: '**', redirectTo: '' },
];
