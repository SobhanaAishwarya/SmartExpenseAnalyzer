# Smart Expense Analyzer

A responsive travel and everyday expense manager. This update preserves the original Java calculator and grows it into a Spring Boot application with sign-up, BCrypt passwords, session login, owner-scoped expense and trip records, budget analytics, admin access, local H2 persistence and optional MySQL/Docker deployment.

## Features

- Register/sign in and manage personal expense records. Expense values are positive INR with up to two decimal places.
- Create/edit/delete expenses; filter by description, category and date.
- Organize trips and associate expenses with them.
- Set a spending budget. Overview shows category totals, highest expense, remaining budget, recent activity, monthly bars and category doughnut charts from saved data.
- Admin overview lists users and activity, permits role changes and user removal. Admin endpoints require an ADMIN session. Password hashes are never returned.
- Environment-backed first admin creation: set ADMIN_EMAIL and ADMIN_PASSWORD (minimum 12 characters) before first start. No default credential is included.

## Stack and project layout

Java 21, Spring Boot 3.3, Spring MVC, Spring Data JPA, BCrypt, Maven, H2 by default, optional MySQL, HTML/CSS/JavaScript, Chart.js, Docker and Jenkins. The layout follows the layered structure in the reference screenshot, with finance domain names and focused files.

```text
.vscode/settings.json
Dockerfile · docker-compose.yml · Jenkinsfile · pom.xml · README.md
data/.gitkeep
src/main/java/com/example/smartexpenseanalyzer/
  config/       DataInitializer, AppConfig, SecurityConfig
  controller/   AdminProfileController, ExpenseController, ProfileController,
                RegistrationController, DashboardController, TripController
  model/        AdminProfile, AppUser, Expense, Trip
  repository/   AdminProfileRepository, AppUserRepository,
                ExpenseRepository, TripRepository
  security/     SessionGuard
  service/      AdminProfileService, AppUserDetailsService, ExpenseService,
                DashboardService, TripService, ApiFacade
  util/         MoneyUtils, SmartExpenseAnalyzer (original calculator)
  SmartExpenseAnalyzerApplication.java
src/main/resources/
  application.properties · application-mysql.properties
  static/       admin.html, index.html, login.html, register.html,
                script.js, style.css, user.html
src/test/java/com/example/smartexpenseanalyzer/
  service/      DashboardServiceTest, ExpenseServiceTest
  util/         SmartExpenseAnalyzerTest
```

Admin is a role on the `User` account rather than a duplicate admin-profile table. The account model replaces the screenshot's generic app-user entity; expenses and trips hold the project-specific records. `SessionGuard` protects API routes and `SecurityConfig` attaches it to Spring MVC. Controllers are separated by feature and delegate business work to ApiFacade and focused services.

## DevOps flow

Jenkins checks out the repository, runs Maven clean/compile/test/package, publishes Surefire test reports and archives the runnable JAR. The Docker stage builds the image when Docker is available on the agent. Docker Compose runs the app with MySQL and a persistent volume; credentials come from `.env` and environment variables. The included pipeline uses Windows `bat` steps to match the original Jenkins setup.

## Run locally

Requirements: Java 21, Maven 3.9+. H2 stores data in `./data/expenses`; no database setup is required.

```powershell
mvn clean test package
mvn spring-boot:run
```

Open http://localhost:8080 and register an account. To create the initial admin account, set the environment variables first:

```powershell
$env:ADMIN_EMAIL = 'admin@example.com'
$env:ADMIN_PASSWORD = 'a-unique-password-of-12-chars-or-more'
mvn spring-boot:run
```

The initializer creates an admin only when that email has not been registered. It does not promote existing accounts. To use from VS Code, open the extracted project directory, set environment variables in the terminal, and run the Maven commands above. The packaged JAR is `target/smart-expense-analyzer-1.0.0.jar`.

## MySQL

Set `SPRING_PROFILES_ACTIVE=mysql`, `DB_URL`, `DB_USER` and `DB_PASSWORD`. Example URL: `jdbc:mysql://localhost:3306/smartexpense?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`. Hibernate schema mode is `update`; back up before production changes.

## Docker Compose

Copy `.env.example` to `.env`, change every sample secret, then run `docker compose up --build`. The app appears at port 8080; MySQL records persist in `mysql_data`. Keep `.env` private; it is excluded from the Docker build context.

For an internet-facing HTTPS deployment, set `COOKIE_SECURE=true` after TLS is active at the reverse proxy. The H2 console is disabled under the MySQL profile. The provided session-based demo is suitable for a learning deployment; add CSRF protection and production-grade secrets/network controls before exposing it to untrusted public traffic.

## Jenkins

The pipeline keeps checkout and Maven compile/test/package, publishes Surefire results and archives the JAR. It attempts an image build when a Docker daemon is available. `bat` steps preserve the original Windows Jenkins agent assumption. Configure Java 21, Maven and Docker on the agent as needed. Jenkins was not available to run this pipeline here.

## Tests and verification

`mvn clean test package` runs seven JUnit tests: total, highest and category calculator checks; budget, category and highest-value dashboard summaries; and expense amount validation. The local HTTP smoke check also covered admin bootstrap/login, user registration, expense creation, dashboard totals and all five HTML entry routes. Docker Compose syntax was checked with required environment variables; neither a live MySQL container nor the external Jenkins environment was available here.

## Explaining the code

- `model`: persistent app users, admin profiles, trips and expenses, with each expense owned by an app user.
- `repository`: database queries; personal expense/trip reads are owner-scoped.
- `controller`: focused API routes used by the browser. `service`: account, expense, trip, dashboard and admin operations.
- `security`: the session guard rejects unauthenticated APIs and non-admin admin routes.
- `config`: BCrypt bean, interceptor registration and environment-configured admin bootstrap. `util/MoneyUtils` centralizes INR calculations.
- `static`: login/register, overview, charts, ledger search/filter, trip UI and admin console.

## Screenshots and future work

No screenshots are bundled; capture them after running and signing in. Possible next steps: CSRF protection, password reset and email verification, pagination/export, database migrations, audit trail and broader integration tests.
