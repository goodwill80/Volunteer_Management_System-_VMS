FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
VOLUME /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw install
CMD ["./mvnw", "spring-boot:run"]


#test for development branch
# FROM eclipse-temurin:17-jdk-jammy
# VOLUME /app
# COPY target/group-1-0.0.1-SNAPSHOT.jar vms.jar
# ENTRYPOINT ["java","-jar","vms.jar"]

#>>>>>>> development-branch-jonathan
