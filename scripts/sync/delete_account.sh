#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./delete_account.sh <account_uuid>"
    exit 1
fi
curl -XDELETE -si "http://localhost:8080/accounts/$1/sync" -H "Content-Type: application/json"
