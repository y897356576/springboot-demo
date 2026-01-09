FROM openjdk:21-jdk-alpine
COPY target/springboot-demo-*.jar /springboot-demo-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot-demo-app.jar"]