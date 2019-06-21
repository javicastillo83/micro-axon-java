#!/usr/bin/env bash
curl -XGET -s "http://localhost:8180/clients" -H "Content-Type: application/json" | jq
