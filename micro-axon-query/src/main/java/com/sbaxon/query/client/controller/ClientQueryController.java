package com.sbaxon.query.client.controller;

import com.sbaxon.query.client.mapper.IClientMapper;
import com.sbaxon.query.client.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
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

}

