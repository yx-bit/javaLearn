FROM openjdk:8-jdk-alpine
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","/app.jar"]