# Library Spring Project

A REST API for managing a library catalog (books, authors, and users), built with Spring Boot and secured with Spring
Security.

This project started as a plain-Java CRUD app using raw JDBC, and was rebuilt from scratch as a Spring Boot application
to learn the framework properly: layered architecture, JPA, validation, exception handling, testing, and authentication.

## Branches

The same API is implemented with two different authentication strategies, kept on separate branches on purpose:

| Branch     | Authentication  | How clients authenticate                                                    |
|------------|-----------------|-----------------------------------------------------------------------------|
| `main`     | HTTP Basic      | Username and password sent with every request                               |
| `auth/jwt` | JWT (stateless) | Log in once at `/api/auth/login`, then send `Authorization: Bearer <token>` |

Switch branches to see each implementation.

## Tech Stack

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Security (BCrypt password hashing, role-based access)
- Bean Validation
- MySQL
- Lombok
- jjwt 0.13 (`auth/jwt` branch only)
- JUnit 5 + Mockito

## Architecture

```
controller  →  service (interface + impl)  →  repository  →  MySQL
                     ↕
              mapper  ↔  DTOs
```

- **Controllers** handle HTTP only and delegate to services.
- **Services** contain the business rules and are split into interfaces and implementations.
- **DTOs** are used for all requests and responses, so entities are never exposed directly.
- **Mappers** are written by hand and convert between DTOs and entities.
- **Exceptions** follow a small hierarchy (`ResourceNotFoundException`, `ResourceConflictException`, ...) handled
  centrally by a `@RestControllerAdvice`, so every error returns the same JSON shape.

## Data Model

- **Author**: `id`, `name`
- **Book**: `id`, `name`, `pages`, `author` (many-to-one, required)
- **User**: `id`, `email` (unique), `userName` (unique), `password` (BCrypt hash), `role` (`USER` or `ADMIN`)

## API

**Access levels:** Public = no login · User = any logged-in user · Admin = `ADMIN` role only

### Auth (`auth/jwt` branch only)

| Method | Path              | Access | Description              |
|--------|-------------------|--------|--------------------------|
| POST   | `/api/auth/login` | Public | Log in and receive a JWT |

### Users

| Method | Path                   | Access | Description                           |
|--------|------------------------|--------|---------------------------------------|
| POST   | `/api/users`           | Public | Register (always created as `USER`)   |
| GET    | `/api/users/{id}`      | User   | Get a user                            |
| PATCH  | `/api/users/{id}`      | User   | Update your **own** email or username |
| PATCH  | `/api/users/{id}/role` | Admin  | Promote a user to admin               |
| DELETE | `/api/users/{id}`      | Admin  | Delete a user                         |

### Authors

| Method | Path                | Access | Description                                                   |
|--------|---------------------|--------|---------------------------------------------------------------|
| POST   | `/api/authors`      | User   | Create an author                                              |
| GET    | `/api/authors/{id}` | User   | Get an author                                                 |
| PUT    | `/api/authors/{id}` | Admin  | Update an author                                              |
| DELETE | `/api/authors/{id}` | Admin  | Delete an author (rejected with 409 if they still have books) |

### Books

| Method | Path                                | Access | Description                               |
|--------|-------------------------------------|--------|-------------------------------------------|
| POST   | `/api/books`                        | User   | Create a book                             |
| GET    | `/api/books/{id}`                   | User   | Get a book                                |
| GET    | `/api/books/search/name?name=`      | User   | Search books by name                      |
| GET    | `/api/books/search/pages?from=&to=` | User   | Find books within a page range            |
| PATCH  | `/api/books/partial/{id}`           | Admin  | Partial update (only the fields you send) |
| PUT    | `/api/books/full/{id}`              | Admin  | Full update                               |
| DELETE | `/api/books/{id}`                   | Admin  | Delete a book                             |

### Example: register and log in (JWT branch)

Register:

```http
POST /api/users
Content-Type: application/json

{
  "email": "reader@example.com",
  "username": "reader",
  "password": "secret123"
}
```

Log in:

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "reader",
  "password": "secret123"
}
```

Response:

```json
{
  "token": "eyJhbGciOi...",
  "id": 2,
  "username": "reader",
  "role": "ROLE_USER"
}
```

Then send the token with every request:

```
Authorization: Bearer eyJhbGciOi...
```

> On `main`, the registration field is `userName` instead of `username`, and there is no login endpoint. You send the
> username and password with each request using HTTP Basic.

### Errors

Every error returns the same shape:

```json
{
  "message": "Book not found with id: 5",
  "status": 404,
  "timestamp": "2026-10-06T19:02:55"
}
```

| Status | When                                                                                               |
|--------|----------------------------------------------------------------------------------------------------|
| 400    | Validation failed (blank fields, invalid email, negative page count, ...)                          |
| 401    | Not logged in, or the token is missing, invalid, or expired                                        |
| 403    | Logged in, but not allowed (e.g. a normal user deleting a book, or editing someone else's account) |
| 404    | Book, author, or user not found                                                                    |
| 409    | Duplicate email or username, or deleting an author who still has books                             |

## Security Notes

- Passwords are hashed with BCrypt and never returned in any response.
- New users are always created with the `USER` role. Clients can't choose their own role.
- On startup, an admin account is created automatically if none exists (configured through environment variables).
- **JWT branch:**
    - Tokens are signed with HMAC and expire after 1 hour.
    - On every request, the filter verifies the token, then loads the user from the database. This means role changes
      take effect immediately, and deleted users are locked out right away, even if their token hasn't expired yet. The
      trade-off is one database lookup per request.
    - Sessions are stateless: the server stores nothing between requests.

## Running Locally

**Requirements:** JDK 21+ and a running MySQL server.

1. Create a database called `test` (or change `spring.datasource.url` in `application.properties`).
2. Set these environment variables:

   | Variable | Purpose | Branch |
         |---|---|---|
   | `DB_PASSWORD` | Your MySQL password | both |
   | `ADMIN_PASSWORD` | Password for the auto-created admin | both |
   | `JWT_SECRET` | Secret key for signing tokens (at least 32 characters) | `auth/jwt` |

   In IntelliJ: **Run → Edit Configurations → Environment variables**.

3. Run:

   ```
   ./mvnw spring-boot:run
   ```

The API starts on `http://localhost:8080`. Tables are created automatically (`ddl-auto=update`, intended for
development).

## Tests

Unit tests use JUnit 5 and Mockito, and cover the book service's create and find paths, including the not-found
branches.

```
./mvnw test
```

Note: `contextLoads` starts the full application, so it needs MySQL running.

## Roadmap

- Google OAuth2 login (planned as a third branch)
- Broader test coverage (author and user services, controllers, security rules)
- List endpoints with pagination
- Borrowing and returning books