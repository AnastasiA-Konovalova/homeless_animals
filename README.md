# Homeless Animals

Платформа для помощи бездомным животным: регистрация найденных/
потерянных животных, назначение волонтёров, обеспечивающих патронаж над животными посредством ежедневного кормления.


## О проекте

**Homeless Animals** - это веб-приложение (backend), которое помогает:

- регистрировать найденных бездомных животных на конкретной локации
- назначать волонтёров-патронов (ответственных за кормление)
- вести учёт статуса животного (под патронажем / не под патронажем)
- предоставлять публичный каталог животных, которым нужна помощь
- учитывать получение волонтерами корма для патронажных животных
- обеспечивает связь волонтер - животное

Цель - упростить взаимодействие между волонтёрами и волонтерской организацией, 
обеспечивающей волонтером кормом для животных.

## Технологический стек

- Java 21, Spring Boot 3.2.2+
- **ORM**: Hibernate / Spring Data JPA
- **Валидация**: Jakarta Bean Validation
- **Документация API**: OpenAPI / Swagger
- **Сборка**: Maven
- **Логирование**: SLF4J + Logback
- **Деплой**: Docker + docker-compose

## Основные сущности

- `Animal` - животное (тип, статус, адрес, волонтер-патрон, количество животных)
- `AnimalType` - справочник видов (кошка, собака)
- `Volunteer` - волонтёр-патрон (имя, телефон, email, статус, список животных под патронажем)
- `AnimalStatus` - enum (UNDER_PATRONAGE, NOT_UNDER_PATRONAGE)
- `VolunteerStatus` - enum (ACTIVE, INACTIVE)

## Быстрый запуск (рекомендуемый способ - через Docker)
1. Перейдите в корень проекта:
   cd C:\Users\admin\IdeaProjects\homeless_animals\animals
2. Запустите приложение:  
```bash
docker compose up -d --build
```
3. Проверьте статус контейнеров:  
```bash
docker compose ps
```
4. Приложение доступно по адресу:  
Swagger UI (документация и тестирование API):
http://localhost:8080/swagger-ui.html  
Actuator health (проверка состояния):
http://localhost:8080/actuator/health
5. Остановка:  
```bash
docker compose down  
```

С удалением данных в базе (если нужно полностью очистить):  
```bash
docker compose down -v
```
Доступ:
Swagger UI:  
http://localhost:8080/swagger-ui.html

## Контроллеры и основные эндпоинты
Полная документация - в Swagger UI.
1. **Волонтёры** (/api/v1/volunteers)  
**GET /api/v1/volunteers/{id}** - получить волонтёра по ID  
**POST /api/v1/volunteers** - создать нового волонтёра (VolunteerNewDto)  
**PATCH /api/v1/volunteers/{id}** - обновить волонтёра (VolunteerUpdateDto)  
**DELETE /api/v1/volunteers/{id}** - удалить волонтёра

2. **Животные** (/api/animals)  
**GET /api/v1/animals** - список всех животных  
**GET /api/v1/animals/{id}** - животное по ID  
**POST /api/v1/animals** - добавить новое животное  
**POST /api/v1/animals/{animalId}/assign** - назначить волонтёра животному  
**DELETE /api/v1/animals/{id}** - удалить животное  
**DELETE /api/v1/animals/{id}/volunteer** - удалить животное у волонтера

## Структура проекта

```text
homeless-animals/
├── src/
│   ├── main/
│   │   ├── java/ru/animals/
│   │   │   ├── controller/           ← REST-контроллеры
│   │   │   ├── dto/                  ← DTO
│   │   │   ├── model/                ← JPA-сущности
│   │   │   ├── exception/            ← кастомные исключения
│   │   │   ├── mapper/               ← мапперы
│   │   │   ├── repository/           ← Spring Data JPA репозитории
│   │   │   ├── service/              ← бизнес-логика
│   │   │   └── AnimalsServiceApp.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/changelog/         ← Flyway / Liquibase миграции
│   └── test/
│       └── java/                     ← тесты
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md