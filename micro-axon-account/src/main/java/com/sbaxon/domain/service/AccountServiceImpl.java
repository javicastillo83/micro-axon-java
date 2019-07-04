package com.sbaxon.domain.service;

import com.sbaxon.domain.command.CreditMoneyCommand;
import com.sbaxon.domain.command.DebitMoneyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AccountServiceImpl implements IAccountService {

    private final CommandGateway commandGateway;

    public AccountServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> credit(String accountUUID, double amount) {
        return commandGateway.send(CreditMoneyCommand.builder()
                                                     .accountUUID(accountUUID)
                                                     .amount(amount)
                                                     .build());
    }

    @Override
    public CompletableFuture<String> debit(String accountUUID, double amount) {
        return commandGateway.send(DebitMoneyCommand.builder()
                                                    .accountUUID(accountUUID)
                                                    .amount(amount)
                                                    .build());
    }

}
