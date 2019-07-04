#!/usr/bin/env bash
curl -XGET -s "http://localhost:8080/clients" -H "Content-Type: application/json" | jq
