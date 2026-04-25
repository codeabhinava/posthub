# 🚀 PostHub - Backend Engineering Assignment

## 📌 Overview
PostHub is a backend system that allows users to create posts, add comments, and interact with content. It includes a real-time virality scoring system using Redis and implements guardrails to control system behavior.

---

## 🛠 Tech Stack
- Java 21
- Spring Boot 4
- PostgreSQL (Persistent storage)
- Redis (Real-time counters & guardrails)

---

## ⚙️ Features

### ✅ Core APIs
- Create Post
- Add Comment (with depth-level control)
- Like Post

### ⚡ Redis Integration
- Real-time virality score
  - Comment → +50
  - Like → +20
- Atomic counters using Redis

### 🚧 Guardrails
- Max comment depth = 20
- Max comments per post = 100 (Redis-based)
- Cooldown system using Redis TTL

---

## 🧠 Architecture

- PostgreSQL → stores actual data (posts, users, comments)
- Redis → handles:
  - Fast counters (virality score)
  - Rate limiting / guardrails
  - Temporary cooldown data

---

## 🔥 Key Design Decisions

- Used Redis for atomic operations to avoid database contention
- Implemented depth-level validation to prevent deep nesting
- Applied Redis TTL for cooldown logic

---

## ▶️ How to Run

### 1. Start services
```bash
docker compose up
