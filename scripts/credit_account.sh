#!/usr/bin/env bash
if [[ -z "$2" ]]
  then
    echo "Usage: ./credit_account.sh <accountUUID> <amount>"
    exit 1
fi
curl -XPUT -i "http://localhost:8180/accounts/$1/credit/$2" -H "Content-Type: application/json"
