#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./update_client.sh <clientUUID>"
    exit 1
fi
curl -XPUT -si "http://localhost:8080/clients/$1" -H "Content-Type: application/json" -d '{"firstName":"client_fn_updated", "lastName":"client_ln", "email":"client@mecalux.com"}'
