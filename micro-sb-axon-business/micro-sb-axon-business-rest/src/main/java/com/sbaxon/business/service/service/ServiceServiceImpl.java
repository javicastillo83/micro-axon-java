package com.sbaxon.business.service.service;

import com.sbaxon.business.service.command.CreateServiceCommand;
import com.sbaxon.business.service.dto.CreateServiceDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ServiceServiceImpl implements IServiceService {

    private final CommandGateway commandGateway;

    public ServiceServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> create(CreateServiceDTO createServiceDTO) {
        String serviceUUID = UUID.randomUUID().toString();
        return commandGateway.send(CreateServiceCommand.builder()
                                                       .serviceUUID(serviceUUID)
                                                       .serviceType(createServiceDTO.getServiceType())
                                                       .name(createServiceDTO.getName())
                                                       .build());
    }
}
