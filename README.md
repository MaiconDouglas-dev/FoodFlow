# FoodFlow
FoodFlow: sistema de delivery (no estilo iFood) para praticar Java/Spring com foco em arquitetura e regras de negócio. Inclui pedidos com state machine, precificação/cupom, pagamentos (idempotência), eventos (Pub/Sub), Postgres+Flyway, testes (JUnit/Testcontainers), Docker e painel admin React.

# FoodFlow

Projeto **Full Stack** (em evolução).  
Nesta primeira etapa (Dia 1), o foco foi montar um **setup profissional local** do backend com:

- **Spring Boot**
- **PostgreSQL via Docker Compose**
- **Flyway** para migrations
- **Actuator** para health checks
- **Testcontainers** para testes com banco isolado

---

## ✅ Status (Dia 1 — Setup profissional)

**Pronto:**
- Banco PostgreSQL sobe via Docker (`docker compose up -d`)
- Backend conecta no banco e aplica migrations do Flyway
- Endpoint de health do Actuator responde (`/actuator/health`)
- `mvn test` executa com sucesso

---

## 🧰 Stack

**Backend**
- Java 21
- Spring Boot 4.x
- Spring Data JPA
- Flyway (migrations)
- Spring Boot Actuator

**Database**
- PostgreSQL 16 (Docker)

**Testes**
- JUnit + Spring Boot Test
- Testcontainers (PostgreSQL 16)

---

## 📁 Estrutura do repositório

```txt
FoodFlow/
  backend/                 # Spring Boot backend
  docker-compose.yml       # PostgreSQL local
  README.md
  docs/                    # (opcional) logs/decisões/erros e acertos
