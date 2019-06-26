#!/usr/bin/env bash
curl -XPOST -si "http://localhost:8080/services" -H "Content-Type: application/json" -d '{"name":"Cuenta Estrella", "serviceType":"CHECKING"}'
