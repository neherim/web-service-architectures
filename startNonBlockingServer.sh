#!/usr/bin/env bash
java -Dservice.twitter.url="http://localhost:9000" -Dservice.reddit.url="http://localhost:9001" -jar spring-webflux/build/libs/spring-webflux-1.0-SNAPSHOT.jar
