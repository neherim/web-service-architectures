#!/usr/bin/env bash
node stub-server/mock-server-reddit.js > reddit.log &
node stub-server/mock-server-twitter.js > twitter.log &
