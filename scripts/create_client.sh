#!/usr/bin/env bash
curl -XPOST -si "http://localhost:8080/clients" -H "Content-Type: application/json" -d '{"name":"client_'$(date +%Y%m%d%H%M%S)'"}'
