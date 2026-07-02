# Comandos sugeridos para subir la migración a GitHub

Desde la raíz de tu repositorio real:

```bash
git checkout -b migracion-maven-multimodulo-completa
```

Copia encima el contenido del ZIP corregido, sin borrar `.git`.

Luego puedes hacer commits por partes:

```bash
git add pom.xml
git commit -m "Crea proyecto padre Maven multi-modulo"

git add */pom.xml
git commit -m "Ajusta pom de microservicios para parent multi-modulo"

git add auth-service user-service ms-catalogo ms-stock ms-pedidos ms-pago ms-envios ms-notificaciones ms-resenas api-gateway eureka-server
git commit -m "Corrige microservicios y agrega modulos faltantes"

git add docs deploy-nativo README.md MIGRACION-REALIZADA.md
git commit -m "Agrega documentacion y ejecucion nativa"
```

Validación:

```bash
mvn clean install -DskipTests
```

Subida:

```bash
git push origin migracion-maven-multimodulo-completa
```
