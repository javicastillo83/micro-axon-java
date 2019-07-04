package com.sbaxon.domain.service;

import java.util.concurrent.CompletableFuture;

public interface IBankServiceService {

    CompletableFuture<String> create(CreateBankService createBankService);
}
