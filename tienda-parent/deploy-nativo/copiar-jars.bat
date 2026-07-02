@echo off
cls
echo ==========================================================
echo COPIANDO JARS A deploy-nativo/apps
echo ==========================================================

if not exist apps mkdir apps

copy ..\eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar apps\eureka-server.jar /Y
copy ..\auth-service\target\auth-service-0.0.1-SNAPSHOT.jar apps\auth-service.jar /Y
copy ..\user-service\target\user-service-0.0.1-SNAPSHOT.jar apps\user-service.jar /Y
copy ..\ms-catalogo\target\ms-catalogo-0.0.1-SNAPSHOT.jar apps\ms-catalogo.jar /Y
copy ..\ms-stock\target\ms-stock-0.0.1-SNAPSHOT.jar apps\ms-stock.jar /Y
copy ..\ms-pedidos\target\ms-pedidos-0.0.1-SNAPSHOT.jar apps\ms-pedidos.jar /Y
copy ..\ms-pago\target\ms-pago-0.0.1-SNAPSHOT.jar apps\ms-pago.jar /Y
copy ..\ms-envios\target\ms-envios-0.0.1-SNAPSHOT.jar apps\ms-envios.jar /Y
copy ..\ms-notificaciones\target\ms-notificaciones-0.0.1-SNAPSHOT.jar apps\ms-notificaciones.jar /Y
copy ..\ms-resenas\target\ms-resenas-0.0.1-SNAPSHOT.jar apps\ms-resenas.jar /Y
copy ..\api-gateway\target\api-gateway-0.0.1-SNAPSHOT.jar apps\api-gateway.jar /Y

echo.
echo JARS COPIADOS.
pause
