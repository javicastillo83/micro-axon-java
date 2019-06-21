#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./credit_account.sh <account_uuid> <amount>"
    exit 1
fi
curl -XPUT -s "http://localhost:8080/accounts/$1/credit/$2" -H "Content-Type: application/json" | jq
