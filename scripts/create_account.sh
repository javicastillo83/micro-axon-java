#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./create_account.sh <clientUuid>"
    exit 1
fi
curl -XPOST -s "http://localhost:8080/accounts" -H "Content-Type: application/json" -d '{"name":"created_'$(date +%Y%m%d%H%M%S)'", "clientUuid": "'$1'"}' | jq
