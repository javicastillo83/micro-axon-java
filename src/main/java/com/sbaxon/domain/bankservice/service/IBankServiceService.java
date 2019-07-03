package com.sbaxon.domain.bankservice.service;

import java.util.concurrent.CompletableFuture;

public interface IBankServiceService {

    CompletableFuture<String> create(CreateBankService createBankService);
}
