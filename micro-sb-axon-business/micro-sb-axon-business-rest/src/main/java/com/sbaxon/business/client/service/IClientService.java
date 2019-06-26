package com.sbaxon.business.client.service;

import com.sbaxon.business.client.dto.CreateClientDTO;
import com.sbaxon.business.client.dto.SubscribeProductDTO;
import com.sbaxon.business.client.dto.UpdateClientDTO;

import java.util.concurrent.CompletableFuture;

public interface IClientService {

    CompletableFuture<String> create(CreateClientDTO createClientDTO);

    CompletableFuture<String> update(String clientUUID, UpdateClientDTO updateClientDTO);

    CompletableFuture<String> subscribeProduct(String clientUUID, SubscribeProductDTO subscribeProductDTO);

    CompletableFuture<String> unSubscribeProduct(String clientUUID, String productUUID);
}
