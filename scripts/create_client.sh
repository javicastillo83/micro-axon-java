#!/usr/bin/env bash
curl -XPOST -i "http://localhost:8080/clients" -H "Content-Type: application/json" -d '{"firstName":"client_fn", "lastName":"client_ln", "email":"client@mecalux.com"}'
