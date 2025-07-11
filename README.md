# Social Network API

REST API для социальной сети, разработанное на Java с использованием Spring Boot 3.4.5, обеспечивающее функционал управления пользователями, постами, друзьями и личными сообщениями.

## 🚀 Возможности

### 👥 Управление пользователями
- Регистрация и аутентификация пользователей
- Получение профиля пользователя по ID или username
- Поиск пользователей по имени и фамилии

### 👨‍👩‍👧‍👦 Система друзей
- Добавление и удаление друзей
- Управление списком друзей

### 📝 Система постов
- Создание, редактирование и удаление постов
- Получение ленты постов друзей
- Просмотр конкретного поста

### 💬 Личные сообщения
- Отправка сообщений между пользователями
- Просмотр истории диалога

## 🛠 Технологический стек

| Технология | Версия | Назначение |
|-----------|--------|------------|
| **Java** | 21 | Основной язык разработки |
| **Spring Boot** | 3.4.5 | Основной фреймворк |
| **Spring JDBC** | - | Работа с базой данных |
| **PostgreSQL** | - | Основная база данных |
| **Docker** | - | Контейнеризация |
| **Gradle (Groovy)** | - | Система сборки |
| **Liquibase** | - | Миграции базы данных |
| **Spock + Groovy** | - | Фреймворк тестирования |
| **Testcontainers** | - | Интеграционное тестирование |

## 📋 Требования

- Java 21+
- Docker и Docker Compose
- PostgreSQL (при локальном запуске без Docker)

## 🏗 Архитектура

Приложение построено на основе **многоуровневой архитектуры**:

```
┌─────────────────────┐
│   REST Controllers  │ ← HTTP API endpoints
├─────────────────────┤
│   Service Layer     │ ← Бизнес-логика
├─────────────────────┤
│   Repository Layer  │ ← Доступ к данным (Spring JDBC)
├─────────────────────┤
│   PostgreSQL DB     │ ← Хранение данных
└─────────────────────┘
```

### Основные модули API:

- **user** - управление пользователями
- **friend** - система друзей
- **post** - управление постами
- **dialog** - личные сообщения
- **login** - аутентификация

## 🚀 Быстрый старт

### Запуск с Docker Compose

1. **Клонируйте репозиторий**
   ```bash
   git clone <repository-url>
   cd social-network
   ```

2. **Запустите приложение**
   ```bash
   docker-compose up --build
   ```

3. **API будет доступно по адресу**
   ```
   http://localhost:8080
   ```

### Локальный запуск

1. **Установите PostgreSQL**
   ```bash
   # Ubuntu/Debian
   sudo apt-get install postgresql postgresql-contrib
   
   # macOS
   brew install postgresql
   ```

2. **Создайте базу данных**
   ```sql
   CREATE DATABASE social_network;
   CREATE USER social_user WITH PASSWORD 'password';
   GRANT ALL PRIVILEGES ON DATABASE social_network TO social_user;
   ```

3. **Настройте переменные окружения**
   ```bash
   export DB_HOST=localhost
   export DB_PORT=5432
   export DB_NAME=social_network
   export DB_USER=social_user
   export DB_PASSWORD=password
   ```

4. **Соберите и запустите приложение**
   ```bash
   ./gradlew bootRun
   ```

## 🔧 Конфигурация

### Основные настройки (application.yml)

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:social_network}
    username: ${DB_USER:social_user}
    password: ${DB_PASSWORD:password}
    driver-class-name: org.postgresql.Driver
  
  liquibase:
    change-log: classpath:db/changelog/db.changelog-master.xml

logging:
  level:
    org.springframework.jdbc: DEBUG
```

### Переменные окружения

| Переменная | Описание | По умолчанию |
|-----------|----------|--------------|
| `DB_HOST` | Хост базы данных | localhost |
| `DB_PORT` | Порт базы данных | 5432 |
| `DB_NAME` | Имя базы данных | social_network |
| `DB_USER` | Пользователь БД | social_user |
| `DB_PASSWORD` | Пароль БД | password |

## 📚 API Документация

### Аутентификация

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "user123",
  "password": "password"
}
```

**Ответ:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Пользователи

**Регистрация**
```http
POST /user/register
Content-Type: application/json

{
  "username": "john_doe",
  "first_name": "John",
  "second_name": "Doe",
  "birthdate": "1990-01-01",
  "biography": "Software Developer",
  "city": "Moscow",
  "password": "securePassword123"
}
```

**Поиск пользователей**
```http
GET /user/search?first_name=John&last_name=Doe
Authorization: Bearer <token>
```

### Посты

**Создание поста**
```http
POST /post/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "text": "Привет, мир! Это мой первый пост."
}
```

**Лента постов**
```http
GET /post/feed?offset=0&limit=10
Authorization: Bearer <token>
```

### Друзья

**Добавить в друзья**
```http
PUT /friend/set/{user_id}
Authorization: Bearer <token>
```

### Сообщения

**Отправить сообщение**
```http
POST /dialog/{user_id}/send
Authorization: Bearer <token>
Content-Type: application/json

{
  "text": "Привет! Как дела?"
}
```

## 🧪 Тестирование

### Запуск тестов

```bash
# Все тесты
./gradlew test

# Только unit тесты
./gradlew test --tests "*Unit*"

# Только интеграционные тесты
./gradlew test --tests "*Integration*"

# Тесты с отчетом о покрытии
./gradlew test jacocoTestReport
```

### Структура тестов

```
src/test/
├── groovy/                    # Spock тесты
│   ├── unit/                  # Unit тесты
│   └── integration/           # Интеграционные тесты
└── resources/
    └── application-test.yml   # Конфигурация для тестов
```

### Пример Spock теста

```groovy
@SpringBootTest
@Testcontainers
class UserServiceSpec extends Specification {
    
    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:15")
            .withDatabaseName("test_db")
            .withUsername("test")
            .withPassword("test")
    
    @Autowired
    UserService userService
    
    def "should create user successfully"() {
        given: "новый пользователь"
        def userRequest = new CreateUserRequest(
            username: "testuser",
            firstName: "Test",
            secondName: "User"
        )
        
        when: "создаем пользователя"
        def result = userService.createUser(userRequest)
        
        then: "пользователь создается успешно"
        result.username == "testuser"
        result.firstName == "Test"
    }
}
```

## 🗃 Структура базы данных

### Основные таблицы

- **users** - пользователи системы
- **posts** - посты пользователей  
- **friendships** - связи дружбы
- **messages** - личные сообщения
- **sessions** - пользовательские сессии

### Миграции Liquibase

```
src/main/resources/db/changelog/
├── db.changelog-master.xml
├── changeset/
│   ├── 001-create-users-table.xml
│   ├── 002-create-posts-table.xml
│   ├── 003-create-friendships-table.xml
│   └── 004-create-messages-table.xml
```

## 🚀 Развертывание

### Docker

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY build/libs/social-network-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      DB_HOST: postgres
      DB_NAME: social_network
      DB_USER: postgres
      DB_PASSWORD: password
    depends_on:
      - postgres

  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: social_network
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

## 🎯 Рекомендации по разработке

### Код-стайл

- Используйте **Java Code Conventions**
- Применяйте **SOLID принципы**
- Покрывайте код тестами (цель: >80%)
- Документируйте публичные API

### Безопасность

- Всегда валидируйте входные данные
- Используйте параметризованные запросы
- Реализуйте rate limiting для API
- Логируйте важные события безопасности

### Производительность

- Используйте пагинацию для больших списков
- Применяйте индексы для часто используемых запросов
- Кэшируйте часто запрашиваемые данные
- Мониторьте производительность запросов

## 🐛 Отладка

### Логирование

```yaml
logging:
  level:
    com.yourcompany.socialnetwork: DEBUG
    org.springframework.jdbc: DEBUG
    org.springframework.web: DEBUG
```

### Полезные эндпоинты для мониторинга

```bash
# Проверка здоровья приложения
curl http://localhost:8080/actuator/health

# Метрики приложения
curl http://localhost:8080/actuator/metrics

# Информация о приложении
curl http://localhost:8080/actuator/info
```

## 🤝 Участие в разработке

1. Форкните репозиторий
2. Создайте feature-ветку (`git checkout -b feature/amazing-feature`)
3. Зафиксируйте изменения (`git commit -m 'Add amazing feature'`)
4. Отправьте в ветку (`git push origin feature/amazing-feature`)
5. Создайте Pull Request

### Требования к коммитам

```
feat: добавить новую функцию
fix: исправить ошибку
docs: обновить документацию
style: форматирование кода
refactor: рефакторинг без изменения функциональности
test: добавить или исправить тесты
chore: вспомогательные задачи
```

## 📄 Лицензия

Этот проект распространяется под лицензией MIT. См. файл `LICENSE` для получения дополнительной информации.

## 👨‍💻 Автор

**Dmitrii** - [email protected]

## 🙏 Благодарности

- Команда Spring Boot за отличный фреймворк
- Сообщество Spock за выразительный фреймворк тестирования
- Команда Testcontainers за упрощение интеграционного тестирования

---

⭐ **Поставьте звездочку проекту, если он был вам полезен!**