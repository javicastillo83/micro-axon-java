package com.sbaxon.domain.service;

import java.util.concurrent.CompletableFuture;

public interface IAccountService {

    CompletableFuture<String> credit(String accountUUID, double amount);
    CompletableFuture<String> debit(String accountUUID, double amount);
}
