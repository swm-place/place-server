#!/bin/zsh

./gradlew clean build
docker-compose -f docker-compose.dev.yaml up --build
