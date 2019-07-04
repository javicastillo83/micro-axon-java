package com.sbaxon.domain.service;

import java.util.concurrent.CompletableFuture;

public interface IClientService {

    CompletableFuture<String> create(CreateClient createClient);

    CompletableFuture<String> update(String clientUUID, UpdateClient updateClient);

    CompletableFuture<String> subscribeProduct(String clientUUID, SubscribeProduct subscribeProduct);

    CompletableFuture<String> unSubscribeProduct(String clientUUID, String productUUID);
}
