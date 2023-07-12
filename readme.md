Build docker image in local Docker registry
mvn package jib:dockerBuild

Build docker image in Docker Hub registry https://hub.docker.com/
mvn package jib:build

Run
docker compose up -d