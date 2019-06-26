package com.sbaxon.business.client.service;

import com.sbaxon.business.client.command.CreateClientCommand;
import com.sbaxon.business.client.command.SubscribeServiceCommand;
import com.sbaxon.business.client.command.UnSubscribeServiceCommand;
import com.sbaxon.business.client.command.UpdateClientCommand;
import com.sbaxon.business.client.dto.CreateClientDTO;
import com.sbaxon.business.client.dto.SubscribeProductDTO;
import com.sbaxon.business.client.dto.UpdateClientDTO;
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
    public CompletableFuture<String> create(CreateClientDTO createClientDTO) {
        String clientUUID = UUID.randomUUID().toString();
        return commandGateway.send(CreateClientCommand.builder()
                                                      .clientUUID(clientUUID)
                                                      .firstName(createClientDTO.getFirstName())
                                                      .lastName(createClientDTO.getLastName())
                                                      .email(createClientDTO.getEmail())
                                                      .build());
    }

    @Override
    public CompletableFuture<String> update(String clientUUID, UpdateClientDTO updateClientDTO) {
        return commandGateway.send(UpdateClientCommand.builder()
                                                      .clientUUID(clientUUID)
                                                      .firstName(updateClientDTO.getFirstName())
                                                      .lastName(updateClientDTO.getLastName())
                                                      .email(updateClientDTO.getEmail())
                                                      .build());
    }

    @Override
    public CompletableFuture<String> subscribeProduct(String clientUUID, SubscribeProductDTO subscribeProductDTO) {
        return commandGateway.send(SubscribeServiceCommand.builder()
                                                            .clientUUID(clientUUID)
                                                            .serviceUUID(subscribeProductDTO.getServiceUUID())
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
