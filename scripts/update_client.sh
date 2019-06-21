#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./update_client.sh <clientUuid>"
    exit 1
fi
curl -XPUT -si "http://localhost:8080/clients/$1" -H "Content-Type: application/json" -d '{"name":"updated_'$(date +%Y%m%d%H%M%S)'"}'
