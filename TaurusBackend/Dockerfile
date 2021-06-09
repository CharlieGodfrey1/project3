FROM adoptopenjdk/openjdk11

ARG JAR_FILE=target/StarterProject-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} StarterProject-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/StarterProject-0.0.1-SNAPSHOT.jar" ]