FROM amazoncorrecto:21-alpine-jdk

COPY target/prestanet-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]