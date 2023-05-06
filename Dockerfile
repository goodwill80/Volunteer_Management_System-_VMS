FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
VOLUME /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw install
CMD ["./mvnw", "spring-boot:run"]



