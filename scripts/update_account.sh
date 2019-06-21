#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./update_account.sh <account_uuid>"
    exit 1
fi
curl -XPUT -si "http://localhost:8080/accounts/$1" -H "Content-Type: application/json" -d '{"name":"updated_'$(date +%Y%m%d%H%M%S)'"}'
