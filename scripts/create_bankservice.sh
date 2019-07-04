#!/usr/bin/env bash
curl -XPOST -i "http://localhost:8280/bankservices" -H "Content-Type: application/json" -d '{"name":"Cuenta Estrella", "bankServiceType":"CHECKING"}'
