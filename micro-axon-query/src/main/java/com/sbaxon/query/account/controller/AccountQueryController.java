package com.sbaxon.query.account.controller;

import com.sbaxon.query.account.mapper.IAccountMapper;
import com.sbaxon.query.account.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountQueryController {

    private final IAccountMapper accountMapper;

    public AccountQueryController(IAccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public List<Account> findAll(){
            return accountMapper.findAll();
    }

}

