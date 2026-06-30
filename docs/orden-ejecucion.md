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
| user-service | bd_cliente  |
| ms-catalogo  | bd_catalogo |
| ms-servicio  | bd_servicio |
| ms-inventario| bd_inventario |
| ms-notificaciones | bd_notificaciones |
| ms-pago  | bd_pago |
| ms-pedidos  | bd_pedidos |
| ms-stock  | bd_stock |

El script de creación se encuentra en:

```text
docs/script-bd.sql
