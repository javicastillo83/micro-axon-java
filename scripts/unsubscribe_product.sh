#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./unsubscribe_product.sh <clientUUID> <productUUID>"
    exit 1
fi
curl -XDELETE -si "http://localhost:8080/clients/$1/products/$2" -H "Content-Type: application/json"
