FROM openjdk:11

COPY mock-service.jar /opt/mock-service/mock-service-1.0.0.jar

CMD ["java", "-jar", "/opt/mock-service/mock-service-1.0.0.jar"]
