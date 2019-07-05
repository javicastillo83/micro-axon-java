#!/usr/bin/env bash
curl -XGET -s "http://localhost:9080/accounts" -H "Content-Type: application/json" | jq
