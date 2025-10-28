# ===== Build stage =====
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy wrapper and pom first for better caching
COPY .mvn/ .mvn/
COPY mvnw.cmd mvnw .
COPY pom.xml .

# Download dependencies
RUN ./mvnw -q -B -DskipTests dependency:go-offline || true

# Copy sources
COPY src ./src

# Build the application
RUN ./mvnw -q -B -DskipTests package

# ===== Runtime stage =====
FROM eclipse-temurin:17-jre

ENV JAVA_OPTS=""
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/task-manager-api-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
