# RespawnConsolas - Endpoints principales

Este documento resume los endpoints principales del sistema **RespawnConsolas**, un proyecto basado en microservicios con Spring Boot, Eureka, API Gateway, Feign Client, MySQL y Swagger.

## Puertos del sistema

| Servicio      | Puerto | Descripción                    |
|---------------| -----: |--------------------------------|
| Eureka Server |   8761 | Servidor de descubrimiento     |
| API Gateway   |   8080 | Punto único de entrada         |
| ms-cliente    |   8081 | Gestión de clientes            |
| ms-consolas   |   8082 | Gestión de consolas            |
| ms-servicio   |   8083 | Gestión de servicios ofrecidos |
| ms-reserva    |   8084 | Gestión de reservas            |
| ms-pago       |   8085 | Gestión de pagos               |

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
| Servicios | http://localhost:8080/api/v1/servicios |
| Reservas  | http://localhost:8080/api/v1/reservas  |
| Pagos     | http://localhost:8080/api/v1/pagos     |

---

# 2. Swagger por microservicio

Swagger se revisa directamente desde cada microservicio:

| Microservicio | Swagger                               |
| ------------- | ------------------------------------- |
| ms-cliente    | http://localhost:8081/swagger-ui.html |
| ms-consolas    | http://localhost:8082/swagger-ui.html |
| ms-servicio   | http://localhost:8083/swagger-ui.html |
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
  "nombre": "Laura Fuentes",
  "telefono": "956789123",
  "correo": "laura.fuentes@correo.cl",
  "direccion": "La Florida 123"
}
```

### Actualizar cliente

```http
PUT http://localhost:8080/api/v1/clientes/1
Content-Type: application/json
```

```json
{
  "nombre": "Laura Fuentes Actualizada",
  "telefono": "956789123",
  "correo": "laura.fuentes@correo.cl",
  "direccion": "La Florida 456"
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
  "nombre": "Milo",
  "raza": "Beagle",
  "edad": 2,
  "peso": 10.4,
  "observacion": "Muy activo",
  "idCliente": 1
}
```

### Actualizar Consolas

```http
PUT http://localhost:8080/api/v1/consolas/1
Content-Type: application/json
```

```json
{
  "nombre": "Milo Actualizado",
  "raza": "Beagle",
  "edad": 3,
  "peso": 11.2,
  "observacion": "Muy activo y juguetón",
  "idCliente": 1
}
```

### Eliminar Consola

```http
DELETE http://localhost:8080/api/v1/consolas/1
```

## Validaciones principales

| Campo       | Validación                         |
| ----------- | ---------------------------------- |
| nombre      | Obligatorio, máximo 100 caracteres |
| raza        | Obligatoria, máximo 80 caracteres  |
| edad        | Obligatoria, entre 0 y 30          |
| peso        | Obligatorio, mayor a 0             |
| observacion | Opcional, máximo 255 caracteres    |
| idCliente   | Obligatorio                        |

## Comunicación con otros microservicios

Antes de crear o actualizar una consola, `ms-consolas` consulta a `ms-cliente` mediante Feign Client para validar que el cliente exista.

```text
ms-consolas → ms-cliente
```

Si el cliente no existe, la consola no se registra.

---

# 5. Microservicio Servicio

## Puerto directo

```text
http://localhost:8083
```

## Por API Gateway

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint                              | Descripción                      |
| ------ | ------------------------------------- | -------------------------------- |
| GET    | /api/v1/servicios                     | Lista todos los servicios        |
| GET    | /api/v1/servicios/{id}                | Busca un servicio por ID         |
| GET    | /api/v1/servicios/activos             | Lista solo los servicios activos |
| GET    | /api/v1/servicios/buscar?nombre=corte | Busca servicios por nombre       |
| POST   | /api/v1/servicios                     | Crea un nuevo servicio           |
| PUT    | /api/v1/servicios/{id}                | Actualiza un servicio existente  |
| DELETE | /api/v1/servicios/{id}                | Elimina un servicio              |

## Ejemplos

### Listar servicios

```http
GET http://localhost:8080/api/v1/servicios
```

### Buscar servicio por ID

```http
GET http://localhost:8080/api/v1/servicios/1
```

### Listar servicios activos

```http
GET http://localhost:8080/api/v1/servicios/activos
```

### Buscar servicio por nombre

```http
GET http://localhost:8080/api/v1/servicios/buscar?nombre=corte
```

### Crear servicio

```http
POST http://localhost:8080/api/v1/servicios
Content-Type: application/json
```

```json
{
  "nombre": "Baño medicado",
  "descripcion": "Baño especial para piel sensible recomendado por veterinario",
  "precio": 22000,
  "duracionMinutos": 70,
  "activo": true
}
```

### Actualizar servicio

```http
PUT http://localhost:8080/api/v1/servicios/1
Content-Type: application/json
```

```json
{
  "nombre": "Lavado premium",
  "descripcion": "Baño completo con shampoo hipoalergénico y secado especial",
  "precio": 16000,
  "duracionMinutos": 55,
  "activo": true
}
```

### Eliminar servicio

```http
DELETE http://localhost:8080/api/v1/servicios/1
```

## Validaciones principales

| Campo           | Validación                           |
| --------------- | ------------------------------------ |
| nombre          | Obligatorio, máximo 100 caracteres   |
| descripcion     | Obligatoria, máximo 255 caracteres   |
| precio          | Obligatorio, igual o superior a 1000 |
| duracionMinutos | Obligatoria, entre 5 y 240 minutos   |
| activo          | Obligatorio                          |

---

# 6. Microservicio Reserva

## Puerto directo

```text
http://localhost:8084
```

## Por API Gateway

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint                                      | Descripción                     |
| ------ | --------------------------------------------- | ------------------------------- |
| GET    | /api/v1/reservas                              | Lista todas las reservas        |
| GET    | /api/v1/reservas/{id}                         | Busca una reserva por ID        |
| GET    | /api/v1/reservas/mascota/{idMascota}          | Lista reservas por mascota      |
| GET    | /api/v1/reservas/estado/{estado}              | Lista reservas por estado       |
| POST   | /api/v1/reservas                              | Crea una nueva reserva          |
| PUT    | /api/v1/reservas/{id}                         | Actualiza una reserva existente |
| PATCH  | /api/v1/reservas/{id}/estado?estado=REALIZADA | Cambia el estado de una reserva |
| DELETE | /api/v1/reservas/{id}                         | Elimina una reserva             |

## Ejemplos

### Listar reservas

```http
GET http://localhost:8080/api/v1/reservas
```

### Buscar reserva por ID

```http
GET http://localhost:8080/api/v1/reservas/1
```

### Listar reservas por mascota

```http
GET http://localhost:8080/api/v1/reservas/mascota/1
```

### Listar reservas por estado

```http
GET http://localhost:8080/api/v1/reservas/estado/AGENDADA
```

### Crear reserva

```http
POST http://localhost:8080/api/v1/reservas
Content-Type: application/json
```

```json
{
  "fecha": "2026-06-20",
  "hora": "15:00:00",
  "observacion": "Reserva creada desde Gateway",
  "idMascota": 1,
  "idServicio": 1
}
```

### Actualizar reserva

```http
PUT http://localhost:8080/api/v1/reservas/1
Content-Type: application/json
```

```json
{
  "fecha": "2026-06-21",
  "hora": "16:30:00",
  "observacion": "Reserva actualizada",
  "idMascota": 1,
  "idServicio": 1
}
```

### Cambiar estado de reserva

```http
PATCH http://localhost:8080/api/v1/reservas/1/estado?estado=REALIZADA
```

### Eliminar reserva

```http
DELETE http://localhost:8080/api/v1/reservas/1
```

## Validaciones principales

| Campo       | Validación                                           |
| ----------- | ---------------------------------------------------- |
| fecha       | Obligatoria, no puede ser anterior a la fecha actual |
| hora        | Obligatoria                                          |
| observacion | Opcional, máximo 255 caracteres                      |
| idMascota   | Obligatorio                                          |
| idServicio  | Obligatorio                                          |

## Comunicación con otros microservicios

Antes de crear o actualizar una reserva:

```text
ms-reserva → ms-mascota
ms-reserva → ms-servicio
```

`ms-reserva` valida que:

* la mascota exista;
* el servicio exista;
* el servicio esté activo.

Si la mascota no existe, la reserva no se registra.

Si el servicio no existe, la reserva no se registra.

Si el servicio está inactivo, la reserva no se registra.

---

# 7. Microservicio Pago

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

# 8. Flujo completo sugerido de prueba

Este flujo permite probar el funcionamiento completo del sistema usando el API Gateway.

## Paso 1: Crear cliente

```http
POST http://localhost:8080/api/v1/clientes
Content-Type: application/json
```

```json
{
  "nombre": "Laura Fuentes",
  "telefono": "956789123",
  "correo": "laura.fuentes@correo.cl",
  "direccion": "La Florida 123"
}
```

## Paso 2: Crear mascota

Usar un `idCliente` existente.

```http
POST http://localhost:8080/api/v1/mascotas
Content-Type: application/json
```

```json
{
  "nombre": "Milo",
  "raza": "Beagle",
  "edad": 2,
  "peso": 10.4,
  "observacion": "Muy activo",
  "idCliente": 1
}
```

## Paso 3: Crear servicio

```http
POST http://localhost:8080/api/v1/servicios
Content-Type: application/json
```

```json
{
  "nombre": "Baño medicado",
  "descripcion": "Baño especial para piel sensible recomendado por veterinario",
  "precio": 22000,
  "duracionMinutos": 70,
  "activo": true
}
```

## Paso 4: Crear reserva

Usar un `idMascota` y un `idServicio` existente.

```http
POST http://localhost:8080/api/v1/reservas
Content-Type: application/json
```

```json
{
  "fecha": "2026-06-20",
  "hora": "15:00:00",
  "observacion": "Reserva creada desde Gateway",
  "idMascota": 1,
  "idServicio": 1
}
```

## Paso 5: Crear pago

Usar un `idReserva` existente.

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

# 9. Flujo de validaciones entre microservicios

## Crear mascota

```text
POST /api/v1/mascotas
        |
        v
ms-mascota consulta ms-cliente
        |
        v
Valida que idCliente exista
```

## Crear reserva

```text
POST /api/v1/reservas
        |
        v
ms-reserva consulta ms-mascota
ms-reserva consulta ms-servicio
        |
        v
Valida que idMascota exista
Valida que idServicio exista
Valida que el servicio esté activo
```

## Crear pago

```text
POST /api/v1/pagos
        |
        v
ms-pago consulta ms-reserva
        |
        v
Valida que idReserva exista
```

---

# 10. Errores esperados de validación

## Cliente inexistente al crear mascota

```json
{
  "nombre": "Max",
  "raza": "Pastor Alemán",
  "edad": 4,
  "peso": 28.5,
  "observacion": "Activo",
  "idCliente": 999
}
```

Resultado esperado:

```text
Error 400 - Cliente no encontrado
```

## Mascota inexistente al crear reserva

```json
{
  "fecha": "2026-06-20",
  "hora": "11:30:00",
  "observacion": "Prueba mascota inexistente",
  "idMascota": 999,
  "idServicio": 1
}
```

Resultado esperado:

```text
Error 400 - Mascota no encontrada
```

## Servicio inexistente al crear reserva

```json
{
  "fecha": "2026-06-20",
  "hora": "11:30:00",
  "observacion": "Prueba servicio inexistente",
  "idMascota": 1,
  "idServicio": 999
}
```

Resultado esperado:

```text
Error 400 - Servicio no encontrado
```

## Reserva inexistente al crear pago

```json
{
  "fechaPago": "2026-06-20",
  "monto": 12000,
  "metodo": "debito",
  "idReserva": 999
}
```

Resultado esperado:

```text
Error 400 - Reserva no encontrada
```

---

# 11. Resumen de comunicaciones Feign

| Servicio origen | Servicio destino | Motivo                                   |
| --------------- | ---------------- | ---------------------------------------- |
| ms-mascota      | ms-cliente       | Validar existencia del cliente           |
| ms-reserva      | ms-mascota       | Validar existencia de la mascota         |
| ms-reserva      | ms-servicio      | Validar existencia y estado del servicio |
| ms-pago         | ms-reserva       | Validar existencia de la reserva         |

---

# 12. Recomendación de uso en clases

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
