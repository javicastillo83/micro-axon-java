#!/usr/bin/env bash
curl -XPOST -i "http://localhost:8080/bankservices" -H "Content-Type: application/json" -d '{"name":"Cuenta Estrella", "bankServiceType":"CHECKING"}'
