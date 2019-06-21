#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./debit_account.sh <account_uuid> <amount>"
    exit 1
fi
curl -XPUT -s "http://localhost:8080/accounts/sync/$1/debit/$2" -H "Content-Type: application/json" | jq
