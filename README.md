# FoodFlow

**FoodFlow** é um sistema de delivery (estilo iFood) criado para praticar **Java + Spring** com foco em **arquitetura**, **domínio rico** e **regras de negócio**.  
O projeto está sendo construído por etapas (“Dias”), com evolução incremental e testes garantindo segurança nas mudanças.


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

## ✅ Status atual — Dia 2 (Domínio de Pedidos e Regras de Negócio)

Nesta etapa foi implementado o **domínio de pedidos** com:

- Agregado de domínio **Order**
- **State machine** de status no próprio domínio (transições + guards)
- Enum **OrderStatus** com os estados do pedido
- Erros de domínio padronizados com **BusinessException + BusinessErrorCode**
- Testes unitários cobrindo cenários **válidos** e **inválidos**
- `./mvnw test` executando com sucesso

---

## 🎯 Objetivo do projeto (em evolução)

- Pedidos com **state machine**
- Precificação e cupons
- Pagamentos (idempotência)
- Eventos (Pub/Sub)
- PostgreSQL + Flyway
- Testes (JUnit + Testcontainers)
- Docker
- Painel admin (React)

## 🧰 Stack

### Backend
- Java 21
- Spring Boot 4.x
- Spring Data JPA
- Flyway (migrations)
- Actuator

### Database
- PostgreSQL 16 (Docker)

### Testes
- JUnit 5
- Spring Boot Test
- Testcontainers (PostgreSQL 16)


---

## 📁 Estrutura do repositório

```txt
FoodFlow/
  backend/                 # Backend Spring Boot
  docker-compose.yml       # PostgreSQL local
  README.md
  docs/                    # (opcional) logs/decisões/erros e acertos

```