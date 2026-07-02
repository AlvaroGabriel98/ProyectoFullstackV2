# Ejecución nativa sin Docker

1. Ejecutar `mvn clean install -DskipTests` desde la raíz del proyecto padre.
2. Entrar a `deploy-nativo/`.
3. Ejecutar `copiar-jars.bat`.
4. Verificar que MySQL esté iniciado y que existan las bases de datos de `docs/bd-general.sql`.
5. Ejecutar `arrancar-nativo.bat`.
6. Verificar Eureka en `http://localhost:8761`.
7. Probar el Gateway en `http://localhost:8080`.

Para detener todo se pueden cerrar las ventanas de cada microservicio o ejecutar `detener-nativo.bat`.
