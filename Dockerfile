# Etapa de construcción
FROM eclipse-temurin:21-jdk as build

# Copia del código de la aplicación en el contenedor
COPY . /app
WORKDIR /app

# Permitir ejecución del script Maven Wrapper
RUN chmod +x mvnw

# Compilar la aplicación y crear el JAR, omitiendo las pruebas
RUN ./mvnw package -DskipTests

# Renombrar y mover el archivo JAR generado
RUN mv -f target/*.jar app.jar

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Argumento de puerto (puede ser pasado en tiempo de compilación)
ARG PORT
# Definir la variable de entorno PORT
ENV PORT=${PORT}

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/app.jar .

# Crear un usuario sin privilegios
RUN useradd runtime
USER runtime

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "app.jar" ]
