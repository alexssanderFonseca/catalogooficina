FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copy gradle files from root context
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy source code for the catalogo module and its dependencies from the root context
COPY src ./src
COPY peca_insumo ./peca_insumo
COPY servico ./servico
COPY shared_kernel ./shared_kernel

RUN chmod +x gradlew
RUN ./gradlew clean build -x test bootJar --no-daemon


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the application jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "app.jar"]