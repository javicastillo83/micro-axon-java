#!/usr/bin/env bash
curl -XGET -s "http://localhost:8280/bankservices" -H "Content-Type: application/json" | jq
