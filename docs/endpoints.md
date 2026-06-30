# RespawnConsolas - Endpoints principales

Este documento resume los endpoints principales del sistema **RespawnConsolas**, un proyecto basado en microservicios con Spring Boot, Eureka, API Gateway, Feign Client, MySQL y Swagger.

## Puertos del sistema


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

# 1. Acceso por API Gateway

El API Gateway permite consumir todos los microservicios desde un único puerto:

```text
http://localhost:8080
```

| Recurso   | URL por Gateway                        |
|-----------|----------------------------------------|
| Clientes  | http://localhost:8080/api/v1/clientes  |
| Consolas  | http://localhost:8080/api/v1/consolas  |
| Reservas  | http://localhost:8080/api/v1/reservas  |
| Pagos     | http://localhost:8080/api/v1/pagos     |

---

# 2. Swagger por microservicio

Swagger se revisa directamente desde cada microservicio:

| Microservicio | Swagger                               |
| ------------- | ------------------------------------- |
| ms-cliente    | http://localhost:8081/swagger-ui.html |
| ms-consolas    | http://localhost:8082/swagger-ui.html|
| ms-reserva    | http://localhost:8084/swagger-ui.html |
| ms-pago       | http://localhost:8085/swagger-ui.html |

---

# 3. Microservicio Cliente

## Puerto directo

```text
http://localhost:8081
```

## Por API Gateway

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint              | Descripción                    |
| ------ | --------------------- | ------------------------------ |
| GET    | /api/v1/clientes      | Lista todos los clientes       |
| GET    | /api/v1/clientes/{id} | Busca un cliente por ID        |
| POST   | /api/v1/clientes      | Crea un nuevo cliente          |
| PUT    | /api/v1/clientes/{id} | Actualiza un cliente existente |
| DELETE | /api/v1/clientes/{id} | Elimina un cliente             |

## Ejemplos

### Listar clientes

```http
GET http://localhost:8080/api/v1/clientes
```

### Buscar cliente por ID

```http
GET http://localhost:8080/api/v1/clientes/1
```

### Crear cliente

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

### Actualizar cliente

```http
PUT http://localhost:8080/api/v1/clientes/1
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

### Eliminar cliente

```http
DELETE http://localhost:8080/api/v1/clientes/1
```

## Validaciones principales

| Campo     | Validación                         |
| --------- | ---------------------------------- |
| nombre    | Obligatorio, máximo 100 caracteres |
| telefono  | Obligatorio, máximo 20 caracteres  |
| correo    | Obligatorio, formato email, único  |
| direccion | Obligatoria, máximo 150 caracteres |

---

# 4. Microservicio Consolas

## Puerto directo

```text
http://localhost:8082
```

## Por API Gateway

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint                             | Descripción                           |
| ------ | ------------------------------------ |---------------------------------------|
| GET    | /api/v1/consolas                     | Lista todas las consolas              |
| GET    | /api/v1/consolas/{id}                | Busca una consola por ID              |
| GET    | /api/v1/consolas/cliente/{idCliente} | Lista consolas asociadas a un cliente |
| POST   | /api/v1/consolas                     | Crea una nueva consola                |
| PUT    | /api/v1/consolas/{id}                | Actualiza una consola existente       |
| DELETE | /api/v1/consolas/{id}                | Elimina una consola                          |

## Ejemplos

### Listar Consolas

```http
GET http://localhost:8080/api/v1/consolas
```

### Buscar Consolas por ID

```http
GET http://localhost:8080/api/v1/consolas/1
```

### Listar Consolas por cliente

```http
GET http://localhost:8080/api/v1/consolas/cliente/1
```

### Crear Consolas

```http
POST http://localhost:8080/api/v1/consolas
Content-Type: application/json
```

```json
{
}
```

### Actualizar Consolas

```http
PUT http://localhost:8080/api/v1/consolas/1
Content-Type: application/json
```

```json
{
}
```

### Eliminar Consola

```http
DELETE http://localhost:8080/api/v1/consolas/1
```


## Comunicación con otros microservicios

Antes de crear o actualizar una consola, `ms-consolas` consulta a `ms-cliente` mediante Feign Client para validar que el cliente exista.

```text
ms-consolas → ms-cliente
```

Si el cliente no existe, la consola no se registra.

---


#. Microservicio Pago

## Puerto directo

```text
http://localhost:8085
```

## Por API Gateway

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint                                 | Descripción                 |
| ------ | ---------------------------------------- | --------------------------- |
| GET    | /api/v1/pagos                            | Lista todos los pagos       |
| GET    | /api/v1/pagos/{id}                       | Busca un pago por ID        |
| GET    | /api/v1/pagos/reserva/{idReserva}        | Lista pagos por reserva     |
| GET    | /api/v1/pagos/estado/{estado}            | Lista pagos por estado      |
| GET    | /api/v1/pagos/metodo/{metodo}            | Lista pagos por método      |
| POST   | /api/v1/pagos                            | Crea un nuevo pago          |
| PUT    | /api/v1/pagos/{id}                       | Actualiza un pago existente |
| PATCH  | /api/v1/pagos/{id}/estado?estado=ANULADO | Cambia el estado de un pago |
| DELETE | /api/v1/pagos/{id}                       | Elimina un pago             |

## Ejemplos

### Listar pagos

```http
GET http://localhost:8080/api/v1/pagos
```

### Buscar pago por ID

```http
GET http://localhost:8080/api/v1/pagos/1
```

### Listar pagos por reserva

```http
GET http://localhost:8080/api/v1/pagos/reserva/1
```

### Listar pagos por estado

```http
GET http://localhost:8080/api/v1/pagos/estado/PAGADO
```

### Listar pagos por método

```http
GET http://localhost:8080/api/v1/pagos/metodo/EFECTIVO
```

### Crear pago

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

### Actualizar pago

```http
PUT http://localhost:8080/api/v1/pagos/1
Content-Type: application/json
```

```json
{
  "fechaPago": "2026-06-21",
  "monto": 15000,
  "metodo": "debito",
  "idReserva": 1
}
```

### Cambiar estado de pago

```http
PATCH http://localhost:8080/api/v1/pagos/1/estado?estado=ANULADO
```

### Eliminar pago

```http
DELETE http://localhost:8080/api/v1/pagos/1
```

## Validaciones principales

| Campo     | Validación                        |
| --------- | --------------------------------- |
| fechaPago | Obligatoria                       |
| monto     | Obligatorio, mayor a 0            |
| metodo    | Obligatorio, máximo 30 caracteres |
| idReserva | Obligatorio                       |

## Comunicación con otros microservicios

Antes de crear o actualizar un pago:

```text
ms-pago → ms-reserva
```

`ms-pago` valida que la reserva exista.

Si la reserva no existe, el pago no se registra.

---


# . Resumen de comunicaciones Feign

| Servicio origen | Servicio destino | Objetivo                                     |
| --------------- | ---------------- | -------------------------------------------- |
| `ms-catalogo`   | `ms-cliente`     | Validar que el cliente exista                |
| `ms-pedido`     | `ms-catalogo`    | Validar que la consola exista                |
| `ms-pago`       | `ms-pedido`      | Validar que el pedido exista                 |

---

#. Recomendación de uso en clases

Para revisar documentación técnica de cada API, usar Swagger directo:

```text
http://localhost:8081/swagger-ui.html
http://localhost:8082/swagger-ui.html
http://localhost:8083/swagger-ui.html
http://localhost:8084/swagger-ui.html
http://localhost:8085/swagger-ui.html
```

Para probar el flujo completo del sistema, usar API Gateway:

```text
http://localhost:8080
```

De esta forma se demuestra:

* documentación individual por microservicio;
* consumo centralizado por API Gateway;
* registro de servicios en Eureka;
* comunicación real mediante Feign Client;
* validaciones entre microservicios;
* separación de bases de datos.
