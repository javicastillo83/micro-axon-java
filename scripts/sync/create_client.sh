#!/usr/bin/env bash
curl -XPOST -s "http://localhost:8080/clients/sync" -H "Content-Type: application/json" -d '{"name":"created_'$(date +%Y%m%d%H%M%S)'"}' | jq
