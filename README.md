# Reactive MongoDB employee API

Java source example using Spring WebFlux and Spring Data reactive MongoDB. Includes employee models, a repository, and an HTTP resource under `/rest/employee`.

## Repository layout

- `ReactiveMongoExample1Application.java`: Spring Boot entry point.
- `model/`: employee and event models.
- `repository/`: reactive persistence interface.
- `resources/`: HTTP endpoints.

## Setup status

This repository contains source files without a Maven or Gradle build. The related [reactivemongoexample1](https://github.com/mhsh88/reactivemongoexample1) repository contains the Maven configuration. These repositories are separate parts of an old experiment, not a verified ready-to-run application.
