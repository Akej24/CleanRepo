FROM maven:3.9.3-eclipse-temurin-11 as build
WORKDIR /build
COPY . .
RUN mvn clean install

FROM openjdk:11-jre-slim
COPY --from=build /build/target/cleanrepo-1.0.0.jar /usr/local/lib/cleanrepo-1.0.0.jar
EXPOSE 8085
CMD ["java", "-jar", "/usr/local/lib/cleanrepo-1.0.0.jar"]
