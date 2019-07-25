package com.sbaxon.query.controller;

import com.sbaxon.query.mapper.IClientMapper;
import com.sbaxon.query.model.Account;
import com.sbaxon.query.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientQueryController {

    private final IClientMapper clientMapper;

    public ClientQueryController(IClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public List<Client> findAll() {
        return clientMapper.findAll();
    }

    @GetMapping("/{clientUUID}/accounts")
    public List<Account> findAccountsByClientUUID(@PathVariable String clientUUID) {
        Client client = clientMapper.findByUUID(clientUUID);
        return clientMapper.accounts(Long.parseLong(client.getId()));
    }

}

