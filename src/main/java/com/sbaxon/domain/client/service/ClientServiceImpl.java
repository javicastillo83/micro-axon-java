package com.sbaxon.domain.client.service;

import com.sbaxon.domain.client.command.CreateClientCommand;
import com.sbaxon.domain.client.command.SubscribeServiceCommand;
import com.sbaxon.domain.client.command.UnSubscribeServiceCommand;
import com.sbaxon.domain.client.command.UpdateClientCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ClientServiceImpl implements IClientService {

    private final CommandGateway commandGateway;

    public ClientServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> create(CreateClient createClient) {
        String clientUUID = UUID.randomUUID().toString();
        return commandGateway.send(CreateClientCommand.builder()
                                                      .clientUUID(clientUUID)
                                                      .firstName(createClient.getFirstName())
                                                      .lastName(createClient.getLastName())
                                                      .email(createClient.getEmail())
                                                      .build());
    }

    @Override
    public CompletableFuture<String> update(String clientUUID, UpdateClient updateClient) {
        return commandGateway.send(UpdateClientCommand.builder()
                                                      .clientUUID(clientUUID)
                                                      .firstName(updateClient.getFirstName())
                                                      .lastName(updateClient.getLastName())
                                                      .email(updateClient.getEmail())
                                                      .build());
    }

    @Override
    public CompletableFuture<String> subscribeProduct(String clientUUID, SubscribeProduct subscribeProduct) {
        return commandGateway.send(SubscribeServiceCommand.builder()
                                                            .clientUUID(clientUUID)
                                                            .bankServiceUUID(subscribeProduct.getBankServiceUUID())
                                                            .build());
    }

    @Override
    public CompletableFuture<String> unSubscribeProduct(String clientUUID, String productUUID) {
        return commandGateway.send(UnSubscribeServiceCommand.builder()
                                                          .clientUUID(clientUUID)
                                                          .productUUID(productUUID)
                                                          .build());
    }

}
