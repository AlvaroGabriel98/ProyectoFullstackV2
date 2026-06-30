# RespawnConsolas

# 1. Objetivo del proyecto

El sistema permite gestionar el flujo completo de atención de una estética canina:

1. Registrar clientes.
2. Registrar consolas en un catalogo.
3. Administrar el inventario (stock).
4. Registrar pedidos de consolas.
5. Registrar pagos asociados al pedido.
6. Registrar notificaciones del sistema.

---

# 2. Arquitectura general

```text
Cliente externo / Postman / Navegador
        |
        v
API Gateway :8080
        |
        +--> user-service      :80xx  -> bd_cliente
        +--> ms-catalogo       :80xx  -> bd_catalogo
        +--> ms-notificaciones :80xx  -> bd_notificaciones
        +--> ms-pago           :80xx  -> bd_pago
        +--> ms-pedidos        :80xx  -> bd_pedidos
        +--> ms-stock          :80xx  -> bd_stock

Eureka Server :8761
```

---

# 3. Microservicios del sistema

| Módulo             | Puerto | Responsabilidad                        |
| ---------------    | -----: | -------------------------------------- |
| `eureka-server`    |   8761 | Registro y descubrimiento de servicios |
| `api-gateway`      |   8080 | Punto único de entrada a las APIs      |
| `user-service`     |   80xx | Administración de clientes             |
| `ms-catalogo`      |   80xx | Administración de mconsolas            |
| `ms-stock`         |   80xx | Administración de inventario           |
| `ms-pedidos`       |   80xx | Administración de pedidos              |
| `ms-pago`          |   80xx | Administración de pagos                |
| `ms-notificaciones`|   80xx | Administración de notificaciones       |
---

# 4. Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Cloud
* Eureka Server
* Eureka Client
* Spring Cloud Gateway
* OpenFeign
* Spring Web
* Spring Data JPA
* MySQL
* XAMPP
* Lombok
* Bean Validation
* Swagger / OpenAPI
* Maven
* VSCode

---

# 5. Estructura del proyecto

```text
ProyectoFullstackV2-parent/
|
├── pom.xml
├── README.md
|
├── docs/
│   ├── script-bd.sql
│   ├── endpoints.md
│   └── orden-ejecucion.md
|
├── eureka-server/
│   ├── pom.xml
│   └── src/
|
├── api-gateway/
│   ├── pom.xml
│   └── src/
|
├── auth-service/
│   ├── pom.xml
│   └── src/
|
├── ms-cliente/
│   ├── pom.xml
│   └── src/
|
├── ms-catalogo/
│   ├── pom.xml
│   └── src/
|
├── ms-notificaciones/
│   ├── pom.xml
│   └── src/
|
├── ms-pago/
│   ├── pom.xml
│   └── src/
|
├── ms-pedidos/
|   ├── pom.xml
|   └── src/
|
├── ms-stock/
|   ├── pom.xml
|   └── src/
```

---

# 6. Bases de datos

El proyecto usa una base de datos independiente por microservicio.

| Microservicio       | Base de datos       | Tabla principal |
| ------------------- | ------------------- | --------------- |
| `user-service`      | `bd_cliente`        | `cliente`       |
| `ms-catalogo`       | `bd_catalogo`       | `consolas`      |
| `ms-notificaciones` | `bd_notificaciones` | `notificaciones`|
| `ms-pago`           | `bd_pago`           | `pago`          |
| `ms-pedidos`        | `bd_pedidos`        | `pedidos`       |
| `ms-stock`          | `bd_stock`          | `stock`         |


El script de creación de bases y datos iniciales se encuentra en:

```text
docs/script-bd.sql
```

---

# 7. Configuración de MySQL

Este proyecto está configurado para usar MySQL mediante XAMPP en el puerto:

```text
3307
```

Ejemplo de configuración usada en los microservicios:

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3307/bd_cliente?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Si el equipo usa MySQL en el puerto `3306`, se debe cambiar la URL en cada microservicio:

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/NOMBRE_BD?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago
```

---

# 8. Orden de ejecución

Antes de levantar los microservicios, se debe iniciar XAMPP y activar MySQL.

Luego se deben ejecutar los servicios en este orden:

| Orden | Servicio           | Puerto |
| ----: | ------------------ | -----: |
|     1 | `user-service`     |   8761 |
|     2 | `ms-catalogo`      |   80xx |
|     3 | `ms-notificaciones`|   80xx |
|     4 | `ms-pago`          |   80xx |
|     5 | `ms-pedidos`       |   80xx |
|     6 | `ms-stock`         |   80xx |
|     7 | `api-gateway`      |   8080 |

     
# 9. Ejecución desde VSCode

Se recomienda usar la extensión **Spring Boot Dashboard** de VSCode.

Desde el panel de Spring Boot se pueden iniciar los servicios uno por uno:

```text
eureka-server
user-service      
ms-catalogo     
ms-notificaciones
ms-pago`          
ms-pedidos       
ms-stock   
api-gateway
```

También se pueden ejecutar desde terminal.

Ejemplo:

```bash
cd eureka-server
mvn spring-boot:run
```

---

# 10. Compilación del proyecto completo

Desde la raíz del proyecto:

```bash
mvn clean install -DskipTests
```

Se usa `-DskipTests` porque los tests automáticos generados por Spring Initializr no están configurados todavía para trabajar con los contextos reales de cada microservicio.

Más adelante se pueden implementar pruebas unitarias y de integración correctamente configuradas.

---

# 11. Eureka Server

La consola de Eureka se encuentra en:

```text
http://localhost:8761
```

Cuando todos los servicios están levantados, deben aparecer registrados:

```text
API-GATEWAY
user-service 
USER-SERVICE
MS-CATALOGO
MS-NOTIFICACIONES
MS-PAGO
MS-PEDIDOS
MS-STOCK     

```

---

# 12. API Gateway

El API Gateway permite consumir todos los microservicios desde el puerto:

```text
http://localhost:8080
```

Rutas principales:

| Recurso       | URL                                      |
| ------------- | ---------------------------------------- |
| Clientes      | `http://localhost:8080/api/v1/clientes`  |
| Consolas      | `http://localhost:8080/api/v1/consolas`  |
| Notificaciones| `http://localhost:8080/api/v1/notificaciones` |
| Pagos         | `http://localhost:8080/api/v1/pagos`     |
| Pedidos       | `http://localhost:8080/api/v1/pedidos`     |
| Stock         | `http://localhost:8080/api/v1/stock`     |
---

# 13. Swagger

Para simplificar el uso en clases, Swagger se revisa directamente por puerto de cada microservicio.

| Microservicio      | Swagger                                 |
| ------------------ | --------------------------------------- |
| `user-service`     | `http://localhost:8081/swagger-ui.html` |
| `ms-catalogo`      | `http://localhost:8082/swagger-ui.html` |
| `ms-notificaciones`| `http://localhost:8083/swagger-ui.html` |
| `ms-pago`          | `http://localhost:8085/swagger-ui.html` |
| `ms-pedidos`       | `http://localhost:8085/swagger-ui.html` |
| `ms-stock`         | `http://localhost:8085/swagger-ui.html` |

El Gateway se usa para consumir APIs, pero no para centralizar Swagger en esta versión.

---

# 14. Comunicación entre microservicios

El proyecto usa OpenFeign para comunicación entre servicios.

| Servicio origen | Servicio destino | Objetivo                                     |
| --------------- | ---------------- | -------------------------------------------- |
| `ms-catalogo`   | `ms-cliente`     | Validar que el cliente exista                |
| `ms-pedido`     | `ms-catalogo`    | Validar que la consola exista                |
| `ms-pago`       | `ms-pedido`      | Validar que el pedido exista                 |

---

# 15. Flujo funcional principal

## Paso 1: Crear cliente

```http
POST http://localhost:8080/api/v1/clientes
Content-Type: application/json
```

```json
{
  "id": "01",
  "first_name": "Alvaro",
  "last_name": "Olate",
  "phone": "956789123",
  "address": "calle falsa 23",
  "city": "Santiago",
  "contry": "Chile"
}
```

## Paso 2: Crear Consola

```http
POST http://localhost:8080/api/v1/consolas
Content-Type: application/json
```

```json
{
  "id": "01",
  "nombre": "Playstation 5",
  "fabricante": "Sony",
  "almacenamiento": "1TB",
  "precio": "599000",
  "stock": "20"
}
```

## Paso 3: Crear Notificaciones

```http
POST http://localhost:8080/api/v1/notificaciones
Content-Type: application/json
```


## Paso 4: Crear pago

```http
POST http://localhost:8080/api/v1/pagos
Content-Type: application/json
```

```json
{
  "fechaPago": "2026-06-20",
  "monto": 12000,
  "metodo": "efectivo",
  "idReserva": 1
}
```

---

# 16. Validaciones implementadas

## `user-service`

* Nombre obligatorio.
* Teléfono obligatorio.
* Dirección obligatoria.

## `ms-consola`

* Nombre obligatorio.
* Marca obligatoria.
* Descripcion obligatoria.

## `ms-pago`

* Fecha de pago obligatoria.
* Monto mayor a 0.
* Método obligatorio.
* Reserva obligatoria.
* Valida que la reserva exista en `ms-reserva`.

---

# 17. Manejo de errores

Cada microservicio incorpora manejo de errores mediante `@RestControllerAdvice`.

Ejemplo de respuesta de error:

```json
{
  "fecha": "2026-06-20T10:30:00",
  "estado": 400,
  "error": "Error de validación",
  "mensaje": "Existen campos inválidos en la solicitud",
  "ruta": "/api/v1/clientes",
  "validaciones": {
    "nombre": "El nombre es obligatorio",
    "correo": "El correo debe tener un formato válido"
  }
}
```

---

# 18. Logs

Cada microservicio incorpora logs básicos mediante Lombok:

```java
@Slf4j
```

Ejemplo:

```java
log.info("Creando cliente");
log.warn("Cliente no encontrado");
log.error("Error al comunicarse con otro microservicio");
```

---

# 19. Comandos útiles

## Compilar todo el proyecto

```bash
mvn clean install -DskipTests
```

## Ejecutar un microservicio desde terminal

```bash
cd ms-catalogo
mvn spring-boot:run
```

## Compilar solo un módulo

```bash
mvn clean install -pl ms-catalogo -DskipTests
```

## Compilar un módulo y sus dependencias

```bash
mvn clean install -pl ms-catalogo -am -DskipTests
```

---

# 20. Documentación adicional

La documentación complementaria se encuentra en:

```text
docs/endpoints.md
docs/orden-ejecucion.md
docs/script-bd.sql
```

---

# 21. Estado actual del proyecto

| Elemento                  | Estado        |
| ------------------------- | ------------- |
| Proyecto padre Maven      | Implementado  |
| Bases de datos MySQL      | Implementadas |
| Eureka Server             | Implementado  |
| API Gateway               | Implementado  |
| `user-service`            | Implementado  |
| `ms-catalogo`             | Implementado  |
| `ms-notificaciones`       | Implementado  |
| `ms-pago`                 | Implementado  |
| `ms-pedidos`              | Implementado  |
| Swagger por microservicio | Implementado  |
| Feign Client              | Implementado  |
| Manejo de errores         | Implementado  |
| Logs                      | Implementado  |
| Frontend web              | Pendiente     |
| Testing                   | Pendiente     |

---
