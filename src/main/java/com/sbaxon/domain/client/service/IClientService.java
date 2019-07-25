package com.sbaxon.domain.client.service;

import java.util.concurrent.CompletableFuture;

public interface IClientService {

    CompletableFuture<String> create(CreateClient createClient);

    CompletableFuture<String> update(String clientUUID, UpdateClient updateClient);

    CompletableFuture<String> remove(String clientUUID);

    CompletableFuture<String> subscribeProduct(String clientUUID, SubscribeProduct subscribeProduct);

    CompletableFuture<String> unSubscribeProduct(String clientUUID, String productUUID);
}
