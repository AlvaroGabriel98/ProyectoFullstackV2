@echo off
title Desarrollo FullStack I - Ejecucion Nativa
cls
echo ==========================================================
echo INICIANDO MICROSERVICIOS - MODO NATIVO SIN DOCKER
echo ==========================================================
echo.
echo Verificando Java instalado...
java -version
echo.
echo IMPORTANTE:
echo - MySQL debe estar iniciado.
echo - Las bases de datos de docs/bd-general.sql deben existir.
echo - Los puertos 8761, 8080, 8081, 8082, 8083, 8084, 8085, 8086, 8087, 8088 y 8089 deben estar disponibles.
echo.
echo [1/3] Iniciando Eureka Server...
start "Eureka Server" cmd /k "java -jar apps/eureka-server.jar"
timeout /t 20 /nobreak > nul

echo [2/3] Iniciando microservicios de negocio...
start "Auth Service" cmd /k "java -jar apps/auth-service.jar"
start "User Service" cmd /k "java -jar apps/user-service.jar"
start "MS Catalogo" cmd /k "java -jar apps/ms-catalogo.jar"
start "MS Stock" cmd /k "java -jar apps/ms-stock.jar"
start "MS Pedidos" cmd /k "java -jar apps/ms-pedidos.jar"
start "MS Pago" cmd /k "java -jar apps/ms-pago.jar"
start "MS Envios" cmd /k "java -jar apps/ms-envios.jar"
start "MS Notificaciones" cmd /k "java -jar apps/ms-notificaciones.jar"
start "MS Resenas" cmd /k "java -jar apps/ms-resenas.jar"
timeout /t 35 /nobreak > nul

echo [3/3] Iniciando API Gateway...
start "API Gateway" cmd /k "java -jar apps/api-gateway.jar"

echo.
echo ==========================================================
echo SISTEMA INICIADO EN MODO NATIVO
echo ==========================================================
echo Eureka: http://localhost:8761
echo Gateway: http://localhost:8080
echo.
pause
