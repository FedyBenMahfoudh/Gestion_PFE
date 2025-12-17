# — Gestion PFE & Stages
JavaFX desktop application for managing end-of-studies projects (PFE) and internships (stages).

At the moment, the project contains a complete **Étudiants** module (CRUD + search) backed by **PostgreSQL**, and UI scaffolding (tabs/screens) for other modules (Encadrants, Projets, Stages, Entreprises, Rapports, Soutenances, Jury).

## Tech stack
- Java 17
- JavaFX 17 (via `javafx-maven-plugin`)
- Maven (wrapper included: `mvnw`, `mvnw.cmd`)
- PostgreSQL (JDBC driver)
- JUnit 5 (test dependencies)

## Features
### Implemented
- Students management (table view)
  - List students (from PostgreSQL)
  - Add student
  - Update student
  - Delete student
  - Search by `matricule`

### In progress / UI skeleton
- Encadrants, Projets, Stages, Entreprises, Rapports, Soutenances, Jury (tabs and placeholders exist in the UI).

## Prerequisites
- JDK 17 installed and available in your IDE/terminal
- PostgreSQL running locally

## Database setup (PostgreSQL)
The application currently connects using the hard-coded settings in `src/main/java/org/example/gest_pfe/config/Database.java`:
- URL: `jdbc:postgresql://localhost:5432/mydb`
- USER: `postgres`
- PASSWORD: `postgres`

Create the database and table (example):

```sql path=null start=null
-- Create DB (run as a superuser)
CREATE DATABASE mydb;

-- Then connect to mydb and create the table
CREATE TABLE IF NOT EXISTS etudiants (
  matricule       INT PRIMARY KEY,
  prenom          VARCHAR(100) NOT NULL,
  nom             VARCHAR(100) NOT NULL,
  date_naissance  DATE NOT NULL,
  email           VARCHAR(255) NOT NULL,
  specialite      VARCHAR(150) NOT NULL,
  parcours        VARCHAR(150) NOT NULL
);
```

If your database name/port/user/password is different, update them in:
- `src/main/java/org/example/gest_pfe/config/Database.java`

## Run the application
### Option A — Maven Wrapper (recommended)
From the project root:

```bat path=null start=null
.\mvnw.cmd clean javafx:run
```

### Option B — From your IDE
Run the main class:
- `org.example.gest_pfe.Launcher`

## Build
Package the project:

```bat path=null start=null
.\mvnw.cmd clean package
```

The project also includes `javafx-maven-plugin` configuration for creating a runtime image via jlink.

## Project structure
- `src/main/java/org/example/gest_pfe/`
  - `Launcher.java`: application entry point (`main` method)
  - `MainApplication.java`: JavaFX `Application`
  - `controllers/`: JavaFX controllers
  - `models/`: model classes (e.g. `Etudiant`)
  - `services/`: data access/services (e.g. `EtudiantService`)
  - `config/Database.java`: PostgreSQL connection
- `src/main/resources/org/example/gest_pfe/`
  - `main.fxml`: main UI (tabs)
  - `etudiantsTab.fxml`, `addStudent.fxml`, `updateStudent.fxml`: Étudiants UI
  - `img/`: images used by the UI

## Troubleshooting
### `mvn javafx:run` fails with `HelloApplication`
`pom.xml` references a `HelloApplication` main class. If you get an error mentioning `HelloApplication`, update the JavaFX plugin configuration to point to the actual entry point (for example `org.example.gest_pfe/org.example.gest_pfe.Launcher`).

### Database connection errors
- Verify PostgreSQL is running
- Verify the database exists (`mydb`) and the `etudiants` table exists
- Update credentials in `src/main/java/org/example/gest_pfe/config/Database.java`

## License
No license specified yet.
