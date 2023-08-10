# SOURCE
FROM alpine as source
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN echo Asia/Seoul > /etc/timezone

WORKDIR /project
COPY . .


# BUILD (INCLUDE TEST)
FROM azul/zulu-openjdk-alpine:17-jre-latest AS build
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN echo Asia/Seoul > /etc/timezone

ARG MARIADB_HOST
ARG MARIADB_PORT
ARG MARIADB_USERNAME
ARG MARIADB_PASSWORD

WORKDIR /project
COPY --from=source project .

RUN ./gradlew clean build


# RUNNER
FROM azul/zulu-openjdk-alpine:17-jre-latest AS runner
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN echo Asia/Seoul > /etc/timezone

LABEL authors="Team Yeoksi"

VOLUME /tmp
WORKDIR /project

ARG JAR_FILE=project/build/libs/*.jar
COPY --from=build ${JAR_FILE} app.jar
COPY private/firebaseServiceAccountKey.json private/firebaseServiceAccountKey.json

ENTRYPOINT ["java", "-jar", "app.jar"]
