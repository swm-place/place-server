FROM azul/zulu-openjdk-alpine:17-jre-latest
LABEL authors="Team Yeoksi"

VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]