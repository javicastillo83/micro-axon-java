#!/usr/bin/env bash
curl -XGET -s "http://localhost:8180/accounts" -H "Content-Type: application/json" | jq
