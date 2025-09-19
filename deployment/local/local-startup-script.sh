#!/bin/bash

## cleanup complete docker environment
docker rm -vf $(docker ps -aq)
docker rmi -f $(docker images -aq)
docker network rm local

## clean up gradle cache and rebuild
./gradlew clean build
## clean up all old images of service
docker image rm olp:latest
docker image rm customflyway:latest

## create newest image of olp Dockerfiles
docker build -t olp:latest ./../../modules/apps/user
docker build -t customflyway:latest ./../../modules/apps/flyway

## run docker compose
docker-compose --env-file .env -f docker-compose-startup.yml up -d