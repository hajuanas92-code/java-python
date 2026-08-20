# Java Spring Boot & AI Integration Playground

A lightweight Spring Boot project built to fulfill internship preparation requirements, focusing on core Java backend basics, REST APIs, and modern AI tool workflows (Claude, Kimi K2.5, Kimi K3).

## Features

* **Spring Boot API Basics:** Full CRUD functionality for managing an in-memory database of books.
* **File Processing:** Endpoint to dynamically upload a PDF and extract text using Apache PDFBox.
* **AI Model Prompting Sandbox:** Structured code layout optimized for integration testing with Claude and Kimi API endpoints.

## Tech Stack & Tooling

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Libraries:** Apache PDFBox (for PDF text parsing)
* **Target AI Integrations:** Claude / Kimi K2.5 / Kimi K3

---

## Quick Start Endpoints

### Book Management
* `POST /book` — Add a new book (Form data: `title`, `author`)
* `GET /book/all` — Retrieve all stored books
* `GET /book/oneBook/{id}` — Get details for a specific book id

### Document & AI Processing
* `POST /book/upload-pdf` — Upload a binary `.pdf` file (`multipart/form-data`). Parses data directly to the server console, acting as a preprocessing layer for LLM context injection.
