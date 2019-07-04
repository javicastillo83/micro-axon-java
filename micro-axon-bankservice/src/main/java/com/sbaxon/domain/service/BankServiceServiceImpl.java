package com.sbaxon.domain.service;

import com.sbaxon.domain.command.CreateBankServiceCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BankServiceServiceImpl implements IBankServiceService {

    private final CommandGateway commandGateway;

    public BankServiceServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> create(CreateBankService createBankService) {
        String bankServiceUUID = UUID.randomUUID().toString();
        return commandGateway.send(CreateBankServiceCommand.builder()
                                                           .bankServiceUUID(bankServiceUUID)
                                                           .bankServiceType(createBankService.getBankServiceType())
                                                           .name(createBankService.getName())
                                                           .build());
    }
}
