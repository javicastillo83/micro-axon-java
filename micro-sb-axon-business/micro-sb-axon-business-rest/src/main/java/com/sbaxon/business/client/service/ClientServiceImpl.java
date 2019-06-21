package com.sbaxon.business.client.service;

import com.sbaxon.business.client.dto.ClientDTO;
import com.sbaxon.business.client.command.CreateClientCommand;
import com.sbaxon.business.client.command.DeleteClientCommand;
import com.sbaxon.business.client.command.UpdateClientCommand;
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
    public CompletableFuture<ClientDTO> create(ClientDTO clientDTO) {
        String uuid = UUID.randomUUID()
                          .toString();
        return commandGateway.send(CreateClientCommand.builder()
                                                       .uuid(uuid)
                                                       .name(clientDTO.getName())
                                                       .build());
    }

    @Override
    public CompletableFuture<ClientDTO> update(String uuid, ClientDTO clientDTO) {
        return commandGateway.send(UpdateClientCommand.builder()
                                                       .uuid(uuid)
                                                       .name(clientDTO.getName())
                                                       .build());
    }

    @Override
    public CompletableFuture<Void> delete(String uuid) {
        return commandGateway.send(DeleteClientCommand.builder()
                                                       .uuid(uuid)
                                                       .build());
    }

    @Override
    public ClientDTO createSync(ClientDTO clientDTO) {
        String uuid = UUID.randomUUID()
                          .toString();
        return commandGateway.sendAndWait(CreateClientCommand.builder()
                                                              .uuid(uuid)
                                                              .name(clientDTO.getName())
                                                              .build());
    }

    @Override
    public ClientDTO updateSync(String uuid, ClientDTO clientDTO) {
        return commandGateway.sendAndWait(UpdateClientCommand.builder()
                                                              .uuid(clientDTO.getUuid())
                                                              .name(clientDTO.getName())
                                                              .build());
    }

    @Override
    public void deleteSync(String uuid) {
        commandGateway.sendAndWait(DeleteClientCommand.builder()
                                                              .uuid(uuid)
                                                              .build());
    }

}
