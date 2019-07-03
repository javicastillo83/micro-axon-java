package com.sbaxon.domain.account.service;

import java.util.concurrent.CompletableFuture;

public interface IAccountService {

    CompletableFuture<String> credit(String accountUUID, double amount);
    CompletableFuture<String> debit(String accountUUID, double amount);
}
