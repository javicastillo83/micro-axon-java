package com.sbaxon.query.controller;

import com.sbaxon.query.mapper.IBankServiceMapper;
import com.sbaxon.query.model.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bankservices")
public class BankServiceQueryController {

    private final IBankServiceMapper bankServiceMapper;

    public BankServiceQueryController(IBankServiceMapper bankServiceMapper) {
        this.bankServiceMapper = bankServiceMapper;
    }

    @GetMapping
    public List<BankService> findAll() {
        return bankServiceMapper.findAll();
    }

}

