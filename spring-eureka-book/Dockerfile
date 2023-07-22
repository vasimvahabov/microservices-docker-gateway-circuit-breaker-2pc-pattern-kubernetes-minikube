FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/spring-eureka-book-0.0.1-SNAPSHOT.jar 
COPY build/libs/spring-eureka-book-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]