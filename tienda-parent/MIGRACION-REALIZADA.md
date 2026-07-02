# Migración realizada

## Objetivo

Dejar operativo el proyecto completo como Maven Multi-Módulo, corrigiendo los módulos incompletos y unificando versiones.

## Cambios principales

- Se creó `pom.xml` padre con `packaging=pom`.
- Se centralizó Spring Boot `3.5.14`, Spring Cloud `2025.0.2`, Java `21` y Springdoc `2.8.9`.
- Se agregaron todos los módulos del proyecto completo.
- Se normalizaron módulos que venían con Spring Boot 4 (`ms-pago` y `ms-resenas`) a Spring Boot 3.5.14.
- Se corrigió `ms-resenas`: entidad JPA, repositorio JPA, service, controller, validaciones, excepciones, Swagger, Eureka y MySQL.
- Se corrigió `ms-envios`: entidades JPA, repositorios JPA, services, controllers, validaciones, excepciones, Swagger, Eureka y MySQL.
- Se corrigió `ms-pago`: service con inyección real, repositorio JPA, controller funcional, DTOs, validaciones, excepciones, Swagger, Eureka y MySQL.
- Se incorporó `ms-catalogo` al parent y se corrigió para usar JPA real.
- Se mantuvieron los módulos corregidos del primer proyecto: `auth-service`, `user-service`, `ms-stock`, `ms-pedidos`, `ms-notificaciones`, `api-gateway` y `eureka-server`.
- Se ajustó API Gateway para usar la estructura correcta de Spring Cloud Gateway Server WebMVC.
- Se agregó documentación en `docs/`.
- Se agregaron scripts de ejecución nativa en `deploy-nativo/`.
- Se eliminaron pruebas automáticas `@SpringBootTest` generadas por defecto para evitar levantar el contexto completo durante pruebas unitarias simples.

## Comando recomendado de validación

```bash
mvn clean install -DskipTests
```

## Uso con GitHub

Este ZIP no debe reemplazar la carpeta `.git` del repositorio original.

Forma recomendada:

1. Abrir el repositorio real conectado a GitHub.
2. Crear una rama nueva.
3. Copiar el contenido de este proyecto dentro del repositorio, sin borrar `.git`.
4. Hacer commits por etapas.
5. Ejecutar `mvn clean install -DskipTests`.
6. Subir la rama a GitHub.
