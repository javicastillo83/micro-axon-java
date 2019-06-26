#!/usr/bin/env bash
if [[ -z "$1" ]]
  then
    echo "Usage: ./subscribe_product.sh <clientUUID> <serviceUUID>"
    exit 1
fi
curl -XPOST -si "http://localhost:8080/clients/$1/products" -H "Content-Type: application/json" -d '{"serviceUUID":"'$2'"}'
