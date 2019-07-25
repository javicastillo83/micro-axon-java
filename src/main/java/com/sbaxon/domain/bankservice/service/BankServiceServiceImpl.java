package com.sbaxon.domain.bankservice.service;

import com.sbaxon.domain.bankservice.command.CreateBankServiceCommand;
import com.sbaxon.domain.bankservice.command.RemoveBankServiceCommand;
import com.sbaxon.domain.bankservice.command.UpdateBankServiceCommand;
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
        String bankServiceUUID = UUID.randomUUID()
                                     .toString();
        return commandGateway.send(CreateBankServiceCommand.builder()
                                                           .bankServiceUUID(bankServiceUUID)
                                                           .bankServiceType(createBankService.getBankServiceType())
                                                           .name(createBankService.getName())
                                                           .build());
    }

    @Override
    public CompletableFuture<String> update(String bankServiceUUID, UpdateBankService updateBankService) {
        return commandGateway.send(UpdateBankServiceCommand.builder()
                                                           .bankServiceUUID(bankServiceUUID)
                                                           .name(updateBankService.getName())
                                                           .bankServiceType(updateBankService.getBankServiceType())
                                                           .build());
    }

    @Override
    public CompletableFuture<String> remove(String bankServiceUUID) {
        return commandGateway.send(RemoveBankServiceCommand.builder()
                                                           .bankServiceUUID(bankServiceUUID)
                                                           .build());
    }
}
