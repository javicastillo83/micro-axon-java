#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./delete_client.sh <clientUuid>"
    exit 1
fi
curl -XDELETE -si "http://localhost:8080/clients/$1" -H "Content-Type: application/json"
