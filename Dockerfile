FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar /app/hospital-management-api.jar
ENTRYPOINT ["java", "-jar","/app/hospital-management-api.jar"]