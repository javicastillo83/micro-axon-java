package com.sbaxon.view.client.controller;

import com.sbaxon.view.client.mapper.IClientMapper;
import com.sbaxon.view.client.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientViewController {

    private final IClientMapper clientMapper;

    public ClientViewController(IClientMapper clientMapper) { 
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public List<Client> findAll(){
            return clientMapper.findAll();
    }

}

