# 🏕️ Ruta Salvaje – API REST

Backend desarrollado con **Java 17** y **Spring Boot** para la gestión de experiencias y planes turísticos.

Este proyecto fue desarrollado como **Proyecto Final del programa Desarrollador Java Full Stack de Generation Colombia**, implementando una arquitectura REST, autenticación mediante **Spring Security + JWT**, persistencia con **PostgreSQL**, almacenamiento de imágenes con **Cloudinary** y despliegue en la nube.

---

# 🚀 Demo

### 🌐 Aplicación Web

👉 https://front-ruta-salvaje.vercel.app/

### 📦 Backend desplegado

👉 https://back-ruta-salvaje.onrender.com

---

# 📂 Repositorios

### Backend

👉 https://github.com/arizacris2431-hash/Api_RutaSalvaje

### Frontend

👉 https://github.com/arizacris2431-hash/Front_rutaSalvaje

---

# 📖 Descripción

Ruta Salvaje es una aplicación web desarrollada en equipo que permite administrar experiencias y planes turísticos mediante una arquitectura cliente-servidor.

Mi participación estuvo enfocada principalmente en el desarrollo del backend utilizando **Java** y **Spring Boot**, implementando:

- APIs REST
- Autenticación y autorización con Spring Security y JWT
- Persistencia con Spring Data JPA y PostgreSQL
- Gestión de imágenes mediante Cloudinary
- Integración con el frontend
- Despliegue de la aplicación

---

# 🛠️ Stack Tecnológico

## Backend

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Maven

## Base de Datos

- PostgreSQL

## Documentación

- Swagger / OpenAPI

## Almacenamiento

- Cloudinary

## Herramientas

- Git
- GitHub
- Postman
- Docker

## Despliegue

- Render
- Vercel

---

# ✨ Funcionalidades

- ✅ Registro de usuarios
- ✅ Inicio de sesión con JWT
- ✅ Autorización mediante roles (ADMIN / USUARIO)
- ✅ CRUD de planes turísticos
- ✅ Gestión de reservas
- ✅ Validación de disponibilidad
- ✅ Gestión de reseñas
- ✅ Gestión de contactos
- ✅ Administración de usuarios
- ✅ Carga y almacenamiento de imágenes con Cloudinary
- ✅ Documentación interactiva con Swagger
- ✅ Persistencia de datos en PostgreSQL

---

# 🏗️ Arquitectura

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
PostgreSQL
```

La aplicación sigue una **arquitectura en capas**, separando responsabilidades para facilitar el mantenimiento, las pruebas y la escalabilidad.

---

# ⚙️ Requisitos

- Java 17 o superior
- PostgreSQL
- Maven 3.9+ o Maven Wrapper
- Cuenta de Cloudinary

---

# 🔧 Variables de entorno

| Variable | Requerida | Descripción |
|-----------|-----------|-------------|
| DATABASE_URL | ✅ | URL JDBC de PostgreSQL |
| JWT_SECRET | ✅ | Clave para firmar los JWT |
| JWT_EXPIRATION | Opcional | Duración del token |
| PORT | Opcional | Puerto del servidor |
| JPA_SHOW_SQL | Opcional | Mostrar consultas SQL |
| SPRING_SECURITY_LOG_LEVEL | Opcional | Nivel de logs |

Ejemplo:

```properties
DATABASE_URL=jdbc:postgresql://localhost:5432/ruta_salvaje?user=postgres&password=tu_clave
```

Las credenciales de Cloudinary deben configurarse mediante variables de entorno y **nunca almacenarse en el repositorio**.

---

# ▶️ Instalación

Clonar el proyecto

```bash
git clone https://github.com/arizacris2431-hash/Api_RutaSalvaje.git
```

Entrar al proyecto

```bash
cd Api_RutaSalvaje
```

Ejecutar

Linux / macOS

```bash
./mvnw spring-boot:run
```

Windows

```powershell
.\mvnw.cmd spring-boot:run
```

La API estará disponible en:

```
http://localhost:8080
```

---

# 🐳 Docker

Construir imagen

```bash
docker build -t ruta-salvaje-api .
```

Ejecutar contenedor

```bash
docker run --rm -p 8080:8080 \
-e DATABASE_URL="jdbc:postgresql://host.docker.internal:5432/ruta_salvaje?user=postgres&password=tu_clave" \
-e JWT_SECRET="reemplaza-por-una-clave-segura" \
ruta-salvaje-api
```

---

# 📑 Documentación de la API

Swagger

```
http://localhost:8080/swagger-ui.html
```

OpenAPI

```
http://localhost:8080/v3/api-docs
```

Una vez desplegado:

👉 https://back-ruta-salvaje.onrender.com/swagger-ui.html

---

# 🔐 Seguridad

La autenticación se realiza mediante **JWT (JSON Web Token)**.

Para consumir rutas protegidas se debe enviar el encabezado:

```http
Authorization: Bearer TU_TOKEN_JWT
```

Los permisos se controlan mediante los roles:

- ADMIN
- USUARIO

---

# 📌 Endpoints principales

## Autenticación

- POST `/auth/register`
- POST `/auth/login`
- PUT `/auth/promover/{email}`

## Planes

- GET `/planes`
- GET `/planes/{id}`
- POST `/planes`
- PUT `/planes/{id}`
- DELETE `/planes/{id}`

## Reservas

- POST `/reservas`
- GET `/reservas/disponibilidad`
- GET `/reservas/usuario/{id}`
- PATCH `/reservas/{id}/cancelar`

## Reseñas

- GET `/resenas`
- POST `/resenas`
- DELETE `/resenas/{id}`

## Usuarios

- GET `/usuarios`
- POST `/usuarios`
- PUT `/usuarios/{id}`
- DELETE `/usuarios/{id}`

## Contactos

- POST `/contactos`
- GET `/contactos`

---

# 📂 Estructura del proyecto

```
src/main/java/com/proyectoFinal/rutaSalvaje

├── auth
├── config
├── controller
├── dto
├── mapper
├── model
├── repository
├── security
└── service
```

---

# 📸 Capturas

> Aquí puedes agregar imágenes de:

- Pantalla de inicio
- Login
- Planes
- Detalle del plan
- Panel administrador
- Swagger

---

# 👨‍💻 Autor

**Cristian Ariza Velasco**

Java Backend Developer

GitHub:
👉 https://github.com/arizacris2431-hash

LinkedIn:
👉 www.linkedin.com/in/cristian-ariza-velasco



---

# 📚 Proyecto académico

Proyecto desarrollado como entrega final del programa **Desarrollador Java Full Stack** de **Generation Colombia**.
