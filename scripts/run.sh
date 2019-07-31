#!/usr/bin/env bash

ACCESS_TOKEN=$1
docker network create \
 --subnet=172.18.0.0/16 \
 mynet

docker run --net mynet --name bo -p 8080:8080 --env ACCESS_TOKEN=${ACCESS_TOKEN} thomasmillergb/github-repo-downloader:1.0.0
