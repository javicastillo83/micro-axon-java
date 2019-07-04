#!/usr/bin/env bash
if [[ -z "$2" ]]
  then
    echo "Usage: ./debit_account.sh <accountUUID> <amount>"
    exit 1
fi
curl -XPUT -i "http://localhost:8180/accounts/$1/debit/$2" -H "Content-Type: application/json"
