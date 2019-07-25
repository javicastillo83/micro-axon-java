package com.sbaxon.domain.bankservice.service;

import java.util.concurrent.CompletableFuture;

public interface IBankServiceService {

    CompletableFuture<String> create(CreateBankService createBankService);

    CompletableFuture<String> update(String bankServiceUUID, UpdateBankService updateBankService);

    CompletableFuture<String> remove(String bankServiceUUID);
}
