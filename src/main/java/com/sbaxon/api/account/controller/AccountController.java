package com.sbaxon.api.account.controller;

import com.sbaxon.domain.account.service.IAccountService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/{accountUUID}/credit/{amount}")
    public CompletableFuture<String> credit(@PathVariable String accountUUID, @PathVariable double amount) {
        return this.accountService.credit(accountUUID, amount);

    }

    @PutMapping("/{accountUUID}/debit/{amount}")
    public CompletableFuture<String> debit(@PathVariable String accountUUID, @PathVariable double amount) {
        return this.accountService.debit(accountUUID, amount);

    }

}

