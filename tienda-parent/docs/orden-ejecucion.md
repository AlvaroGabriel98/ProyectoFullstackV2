# RespawnConsolas - Orden de ejecución

## Requisitos previos

Antes de levantar el sistema se debe tener iniciado:

- XAMPP
- MySQL en puerto 3307
- Eureka Server
- Microservicios de negocio
- API Gateway

## Bases de datos

El proyecto usa una base de datos independiente por microservicio:

| Microservic | Base  datos |
|-------------|-------------|
| ms-cliente  | bd_cliente  |
| ms-consolas | bd_consolas |
| ms-servicio | bd_servicio |
| ms-reserva  | bd_reserva  |
| ms-pago     | bd_pago     |

El script de creación se encuentra en:

```text
docs/script-bd.sql
