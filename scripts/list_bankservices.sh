#!/usr/bin/env bash
curl -XGET -s "http://localhost:8080/bankservices" -H "Content-Type: application/json" | jq
