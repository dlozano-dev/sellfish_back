# Etapa 1: Compilar el proyecto usando Maven con Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación usando JDK 21 más liviano
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (ajústalo si tu app usa otro)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]