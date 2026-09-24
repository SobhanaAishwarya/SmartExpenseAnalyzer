# SmartExpenseAnalyzer

A small Java expense tracker set up as a build-and-test exercise: a Maven project with JUnit 5 tests and a Jenkins
pipeline that builds, tests and packages it on every run.

Java 21 · Maven · JUnit 5 · Jenkins

## What it does

`SmartExpenseAnalyzer` records expenses (category, description, amount) and reports:

- the total spent
- the single highest expense
- the total for a given category (case-insensitive)

## CI pipeline

The `Jenkinsfile` defines a declarative pipeline:

```
Checkout ─► Build (mvn clean compile) ─► Test (mvn test) ─► Package (mvn package)
                                                                   │
                                        email notification on success / failure
```

The pipeline uses `bat` steps, so it expects a Windows Jenkins agent with
Maven on the path and the Email Extension plugin configured.

## Run it locally

```bash
mvn test                                        # run the JUnit tests
mvn package                                     # build the jar
java -cp target/classes SmartExpenseAnalyzer    # run the demo in main()
```

## Project layout

```
├── pom.xml                                 # Java 21, JUnit Jupiter 5.10, Surefire
├── Jenkinsfile                             # CI pipeline
└── src/
    ├── main/java/SmartExpenseAnalyzer.java
    └── test/java/SmartExpenseAnalyzerTest.java
```
