FROM openjdk:17-jre-slim

COPY build/libs/*.jar app.jar

# PUERTO CAMBIADO A 8091
EXPOSE 8091

ENTRYPOINT ["java","-jar","/app.jar"]