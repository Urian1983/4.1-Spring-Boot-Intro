# User API - REST API Simple

API REST para la gestión básica de usuarios desarrollada con Spring Boot. Implementa operaciones CRUD utilizando una lista en memoria (sin base de datos) y demuestra los fundamentos de Spring Boot y desarrollo de APIs REST.

## 🚀 Características

- API RESTful completamente funcional
- Gestión de usuarios en memoria (ArrayList)
- Operaciones CRUD básicas (Create, Read)
- Búsqueda de usuarios por nombre (filtrado)
- Generación automática de IDs con UUID
- Endpoint de health check
- Tests unitarios con MockMvc
- Sin dependencias de base de datos

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Web** - Para crear endpoints REST
- **Maven** - Gestión de dependencias
- **JUnit 5** - Testing
- **MockMvc** - Tests de controladores

## 📋 Requisitos Previos

- JDK 21
- Maven 3.9 o superior

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/userAPI.git
cd userAPI
```

2. **Compilar el proyecto**
```bash
./mvnw clean install
```

3. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en `http://localhost:9000`

## 📚 Endpoints de la API

### Health Check

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/health` | Verificar estado de la aplicación |

**Respuesta:**
```json
{
  "status": "ok"
}
```

### Gestión de Usuarios

| Método | Endpoint | Descripción | Request Body |
|--------|----------|-------------|--------------|
| GET | `/users` | Obtener todos los usuarios | - |
| GET | `/users?name={name}` | Buscar usuarios por nombre | - |
| GET | `/users/{id}` | Obtener un usuario por ID | - |
| POST | `/users` | Crear un nuevo usuario | `CreateUserRequest` |

### Modelo de Datos

**User (Response):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john@example.com"
}
```

**CreateUserRequest:**
```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

### Ejemplos de Uso

**Verificar estado de la API:**
```bash
curl -X GET http://localhost:9000/health
```

**Crear un nuevo usuario:**
```bash
curl -X POST http://localhost:9000/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com"
  }'
```

**Obtener todos los usuarios:**
```bash
curl -X GET http://localhost:9000/users
```

**Buscar usuarios por nombre:**
```bash
curl -X GET "http://localhost:9000/users?name=john"
```

**Obtener un usuario por ID:**
```bash
curl -X GET http://localhost:9000/users/550e8400-e29b-41d4-a716-446655440000
```

### Manejo de Errores

**Usuario no encontrado (404):**
```json
{
  "timestamp": "2024-12-14T21:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User with ID not found",
  "path": "/users/550e8400-e29b-41d4-a716-446655440000"
}
```

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/cat/itacademy/s04/t01/userapi/
│   │   ├── controllers/
│   │   │   ├── CreateUserRequest.java
│   │   │   ├── HealthController.java
│   │   │   ├── HealthResponse.java
│   │   │   ├── UserController.java
│   │   │   └── UserResponse.java
│   │   ├── exceptions/
│   │   │   └── UserNotFoundException.java
│   │   ├── model/
│   │   │   └── User.java
│   │   └── UserApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/cat/itacademy/s04/t01/userapi/
        ├── controllers/
        │   ├── HealthControllerTest.java
        │   └── UserControllerTest.java
        └── UserApiApplicationTests.java
```

## 🔧 Configuración

### application.properties

```properties
spring.application.name=userAPI
server.port=9000
```

La aplicación se ejecuta en el puerto **9000** (no el predeterminado 8080).

## 🧪 Tests

Ejecutar los tests:

```bash
./mvnw test
```

El proyecto incluye:

**HealthControllerTest:**
- Verificación del endpoint de health check

**UserControllerTest:**
- Obtener lista vacía inicialmente
- Crear usuario y verificar respuesta
- Obtener usuario por ID
- Manejo de usuario no encontrado (404)
- Filtrado de usuarios por nombre

## 🎯 Características Técnicas

### Almacenamiento en Memoria
Los usuarios se almacenan en una lista estática (`ArrayList`) en el controlador:
```java
private static final List<User> users = new ArrayList<>();
```

⚠️ **Nota importante:** Los datos **no persisten** al reiniciar la aplicación, ya que no hay base de datos.

### Generación de IDs
Los IDs se generan automáticamente usando `UUID.randomUUID()`:
```java
UUID id = UUID.randomUUID();
User newUser = new User(id, request.name(), request.email());
```

### Records de Java
El proyecto utiliza **records** de Java para DTOs inmutables:
- `CreateUserRequest`
- `HealthResponse`
- `UserResponse`

### Manejo de Excepciones
Excepción personalizada `UserNotFoundException` con anotación `@ResponseStatus(HttpStatus.NOT_FOUND)` que automáticamente devuelve un 404.

### Búsqueda de Usuarios
El endpoint GET `/users` acepta un parámetro opcional `name` para filtrar usuarios:
```java
@GetMapping
public List<User> getUsers(@RequestParam(required = false) String name)
```

Si se proporciona, busca usuarios cuyo nombre contenga el texto (case-insensitive).

## 💡 Conceptos Demostrados

Este proyecto es ideal para aprender:

1. **Fundamentos de Spring Boot**
   - Configuración básica
   - Estructura de un proyecto Spring Boot
   - Anotaciones principales

2. **REST API**
   - Controllers y mappings (`@RestController`, `@GetMapping`, `@PostMapping`)
   - Path variables (`@PathVariable`)
   - Request parameters (`@RequestParam`)
   - Request body (`@RequestBody`)

3. **Testing**
   - Tests de controladores con MockMvc
   - `@WebMvcTest` para tests de slice
   - Assertions con JsonPath

4. **Buenas Prácticas**
   - Separación entre Request/Response DTOs y Model
   - Manejo centralizado de excepciones
   - Uso de Records para inmutabilidad

## 🚀 Próximos Pasos (Mejoras Posibles)

- [ ] Añadir operaciones UPDATE y DELETE
- [ ] Implementar persistencia con base de datos (JPA/H2)
- [ ] Añadir validación de datos con Bean Validation
- [ ] Implementar paginación para listar usuarios
- [ ] Añadir documentación con Swagger/OpenAPI
- [ ] Implementar manejo global de excepciones con `@RestControllerAdvice`

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Añade nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 👤 Autor

Josep J. Roca - IT Academy - Sprint 4 - Task 1 (Niveles 1 y 2)

## 📝 Notas del Proyecto

- Este es un proyecto educativo que demuestra conceptos básicos de Spring Boot
- Los datos se almacenan en memoria y se pierden al reiniciar la aplicación
- El proyecto no incluye autenticación ni autorización
- Es la base para proyectos más avanzados con persistencia y funcionalidades adicionales

---

**Sprint 4 - Task 1** - Introducción a Spring Boot y REST APIs
