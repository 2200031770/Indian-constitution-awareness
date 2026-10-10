# Samvidhan — The People's Constitution

An accessible learning application about the Constitution of India. The
Angular frontend and Spring Boot REST API work together to provide searchable
learning guides and an interactive knowledge check.

## What's included

- Responsive Angular application with topic search, category filters, guide
  pages, and an eight-question quiz.
- Java 17 / Spring Boot 3 API with validated quiz submissions and server-side
  scoring.
- Seeded educational material for six constitutional topics.
- MySQL is the only runtime database; topics and quiz questions are stored
  persistently in MySQL.
- H2 is used only by automated tests.
- Quiz answer keys are kept on the server until answers are submitted.

## Run the application on Windows

Install and start MySQL Server, then open MySQL Workbench or a MySQL terminal
and create the application database:

```sql
CREATE DATABASE constitution_awareness
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

Open two PowerShell terminals from the project root. In Terminal 1, set the
credentials for your local MySQL user and start the backend:

**Terminal 1 — backend:**

```powershell
cd D:\paic\backend
$env:DB_URL = "jdbc:mysql://localhost:3306/constitution_awareness"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-local-mysql-password"
.\mvnw.cmd spring-boot:run
```

Replace the username and password with your MySQL credentials. The backend
does not fall back to an in-memory database: MySQL must be running and the
database must exist. Hibernate creates or updates the application's tables.
On first startup, the application adds the sample topics and quiz questions to
empty tables. They remain in MySQL after the backend is stopped.

**Terminal 2 — frontend:**

```powershell
cd D:\paic\frontend
npm start
```

Open `http://localhost:4200`. Angular's development proxy forwards `/api`
requests to Spring Boot at `http://localhost:8080`, so the browser uses one
origin and no separate CORS configuration is required for local development.
Keep real credentials outside source control. See [backend setup](backend/HELP.md)
for the API details.

## Verify

```powershell
cd D:\paic\backend
.\mvnw.cmd test

cd D:\paic\frontend
npm test -- --watch=false
npm run build
```

## Learning API

| Method | Endpoint | Purpose |
| --- | --- | --- |
| `GET` | `/api/v1/topics` | List learning guides |
| `GET` | `/api/v1/topics/{slug}` | Read a guide |
| `GET` | `/api/v1/quiz/questions` | Get quiz questions and choices |
| `POST` | `/api/v1/quiz/submit` | Submit answers and receive a score with explanations |

Quiz submissions use JSON such as `{"answers":{"1":"A","2":"C"}}`. Unanswered
questions count as incorrect. The guides are for general awareness and are not
legal advice.
