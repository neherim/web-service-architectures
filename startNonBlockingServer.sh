#!/usr/bin/env bash
java -Dservice.twitter.url="http://localhost:9000" -Dservice.reddit.url="http://localhost:9001" -jar dist/spring-webflux-1.0.jar
