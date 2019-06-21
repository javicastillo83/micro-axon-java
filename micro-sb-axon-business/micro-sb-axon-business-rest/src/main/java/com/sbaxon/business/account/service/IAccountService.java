package com.sbaxon.business.account.service;

import com.sbaxon.business.account.dto.AccountDTO;

import java.util.concurrent.CompletableFuture;

public interface IAccountService {

    CompletableFuture<AccountDTO> create(AccountDTO accountDTO);
    CompletableFuture<AccountDTO> update(String uuid, AccountDTO accountDTO);
    CompletableFuture<Void> delete(String uuid);

    CompletableFuture<Void> credit(String uuid, double amount);

    CompletableFuture<Void> debit(String uuid, double amount);

    AccountDTO createSync(AccountDTO accountDTO);
    AccountDTO updateSync(String uuid, AccountDTO accountDTO);
    void deleteSync(String uuid);

    CompletableFuture<AccountDTO> creditSync(String uuid, double amount);

    CompletableFuture<AccountDTO> debitSync(String uuid, double amount);
}
