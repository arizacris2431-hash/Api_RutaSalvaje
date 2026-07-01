# Ruta Salvaje — API REST

API backend para la gestión de experiencias y planes de aventura. Permite administrar usuarios, planes, reservas, reseñas y mensajes de contacto mediante una API REST protegida con JWT y roles.

## Características

- Registro e inicio de sesión con JWT.
- Roles `USUARIO` y `ADMIN`.
- Gestión de planes e imágenes con Cloudinary.
- Reservas, cancelaciones y consulta de disponibilidad.
- Reseñas asociadas a planes.
- Gestión administrativa de usuarios y contactos.
- Documentación Swagger/OpenAPI.
- Persistencia en PostgreSQL.
- Despliegue con Docker.

## Tecnologías

Java 17 · Spring Boot 4 · Spring Security · Spring Data JPA · PostgreSQL · JJWT · Swagger/OpenAPI · Cloudinary · Maven · Docker

## Requisitos

- JDK 17 o superior
- PostgreSQL
- Maven 3.9 o Maven Wrapper
- Cuenta de Cloudinary

## Instalación

```bash
git clone https://github.com/arizacris2431-hash/Api_RutaSalvaje.git
cd Api_RutaSalvaje
```

Crea una base de datos PostgreSQL y configura estas variables:

| Variable | Requerida | Descripción | Predeterminado |
|---|---:|---|---|
| `DATABASE_URL` | Sí | URL JDBC de PostgreSQL | — |
| `JWT_SECRET` | Sí | Clave segura para firmar los JWT | — |
| `JWT_EXPIRATION` | No | Duración del token en milisegundos | `86400000` |
| `PORT` | No | Puerto del servidor | `8080` |
| `JPA_SHOW_SQL` | No | Muestra SQL en consola | `false` |
| `SPRING_SECURITY_LOG_LEVEL` | No | Nivel de logs de seguridad | `INFO` |

Ejemplo de `DATABASE_URL`:

```text
jdbc:postgresql://localhost:5432/ruta_salvaje?user=postgres&password=tu_clave
```

> Las credenciales de Cloudinary deben manejarse mediante variables de entorno y nunca guardarse en el código.

### Ejecutar

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

## Docker

```bash
docker build -t ruta-salvaje-api .
docker run --rm -p 8080:8080 \
  -e DATABASE_URL="jdbc:postgresql://host.docker.internal:5432/ruta_salvaje?user=postgres&password=tu_clave" \
  -e JWT_SECRET="reemplaza-por-una-clave-segura" \
  ruta-salvaje-api
```

## Documentación

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- API desplegada: [back-ruta-salvaje.onrender.com](https://back-ruta-salvaje.onrender.com)

Las rutas protegidas requieren:

```http
Authorization: Bearer TU_TOKEN_JWT
```

## Endpoints

### Autenticación

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `POST` | `/auth/register` | Público | Registra un usuario |
| `POST` | `/auth/login` | Público | Devuelve un token JWT |
| `PUT` | `/auth/promover/{email}` | ADMIN | Promueve un usuario a administrador |

### Planes

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `GET` | `/planes` | Público | Lista los planes |
| `GET` | `/planes/{id}` | Público | Consulta un plan |
| `POST` | `/planes` | ADMIN | Crea un plan con imagen (`multipart/form-data`) |
| `PUT` | `/planes/{id}` | ADMIN | Actualiza un plan |
| `DELETE` | `/planes/{id}` | ADMIN | Elimina un plan |

### Reservas

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `POST` | `/reservas` | Autenticado | Crea una reserva |
| `GET` | `/reservas/disponibilidad?planId={id}&fecha={fecha}` | Autenticado | Valida disponibilidad usando fecha ISO-8601 |
| `GET` | `/reservas/usuario/{usuarioId}` | Propietario o ADMIN | Lista reservas de un usuario |
| `PATCH` | `/reservas/{id}/cancelar-usuario?usuarioId={usuarioId}` | Propietario o ADMIN | Cancela una reserva del usuario |
| `GET` | `/reservas` | ADMIN | Lista todas las reservas |
| `GET` | `/reservas/{id}` | ADMIN | Consulta una reserva |
| `PUT` | `/reservas/{id}` | ADMIN | Actualiza una reserva |
| `PATCH` | `/reservas/{id}/cancelar` | ADMIN | Cancela una reserva |

### Reseñas

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `GET` | `/resenas` | Público | Lista las reseñas |
| `GET` | `/resenas/{id}` | Público | Consulta una reseña |
| `GET` | `/resenas/plan/{planId}` | Público | Lista reseñas de un plan |
| `POST` | `/resenas` | Autenticado | Crea una reseña |
| `DELETE` | `/resenas/{id}` | ADMIN | Elimina una reseña |

### Usuarios

Todas estas rutas requieren rol `ADMIN`.

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/usuarios` | Crea un usuario |
| `GET` | `/usuarios` | Lista los usuarios |
| `GET` | `/usuarios/{id}` | Consulta un usuario |
| `PUT` | `/usuarios/{id}` | Actualiza un usuario |
| `DELETE` | `/usuarios/{id}` | Elimina un usuario |

### Contactos

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| `POST` | `/contactos` | Público | Envía un mensaje |
| `GET` | `/contactos` | ADMIN | Lista los mensajes |
| `GET` | `/contactos/{id}` | ADMIN | Consulta un mensaje |
| `DELETE` | `/contactos/{id}` | ADMIN | Elimina un mensaje |

## Ejemplos

### Registro

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana Pérez",
    "email": "ana@example.com",
    "password": "UnaClaveSegura123",
    "telefono": "3001234567",
    "nombreEmergencia": "Carlos Pérez",
    "telefonoEmergencia": "3009876543",
    "parentesco": "Hermano"
  }'
```

### Inicio de sesión

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"ana@example.com","password":"UnaClaveSegura123"}'
```

### Ruta protegida

```bash
curl http://localhost:8080/reservas/usuario/1 \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

## Estructura

```text
src/main/java/com/proyectoFinal/rutaSalvaje/
├── auth/          # Registro e inicio de sesión
├── config/        # Seguridad, CORS, Swagger y excepciones
├── controller/    # Endpoints REST
├── dto/           # Objetos de entrada y salida
├── mapper/        # Conversión entre entidades y DTO
├── model/         # Entidades y enumeraciones
├── repository/    # Acceso a datos
├── security/      # Filtro y utilidades JWT
└── service/       # Lógica de negocio y Cloudinary
```

## Pruebas

```bash
./mvnw test
```

En Windows: `.\mvnw.cmd test`.

## Seguridad

- Usa un `JWT_SECRET` extenso y aleatorio.
- No publiques contraseñas, claves ni secretos.
- Configura CORS para los dominios reales del frontend.
- Rota cualquier credencial expuesta en el historial de Git.
- Usa HTTPS en producción.

## Autor

Desarrollado por [arizacris2431-hash](https://github.com/arizacris2431-hash).
