package com.sbaxon.view.account.controller;

import com.sbaxon.view.account.mapper.IAccountMapper;
import com.sbaxon.view.account.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountViewController {

    private final IAccountMapper accountMapper;

    public AccountViewController(IAccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public List<Account> findAll(){
            return accountMapper.findAll();
    }

}

