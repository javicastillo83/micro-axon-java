package com.sbaxon.business.account.service;

import com.sbaxon.business.account.command.*;
import com.sbaxon.business.account.dto.AccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountServiceImpl implements IAccountService {

    private final CommandGateway commandGateway;

    public AccountServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<AccountDTO> create(AccountDTO accountDTO) {
        String uuid = UUID.randomUUID()
                          .toString();
        return commandGateway.send(CreateAccountCommand.builder()
                                                       .uuid(uuid)
                                                       .name(accountDTO.getName())
                                                       .build());
    }

    @Override
    public CompletableFuture<AccountDTO> update(String uuid, AccountDTO accountDTO) {
        return commandGateway.send(UpdateAccountCommand.builder()
                                                       .uuid(uuid)
                                                       .name(accountDTO.getName())
                                                       .build());
    }

    @Override
    public CompletableFuture<Void> delete(String uuid) {
        return commandGateway.send(DeleteAccountCommand.builder()
                                                       .uuid(uuid)
                                                       .build());
    }

    @Override
    public CompletableFuture<Void> credit(String uuid, double amount) {
        return commandGateway.send(CreditMoneyCommand.builder()
                                                     .uuid(uuid)
                                                     .amount(amount)
                                                     .build());
    }

    @Override
    public CompletableFuture<Void> debit(String uuid, double amount) {
        return commandGateway.send(DebitMoneyCommand.builder()
                                                    .uuid(uuid)
                                                    .amount(amount)
                                                    .build());
    }

    @Override
    public AccountDTO createSync(AccountDTO accountDTO) {
        String uuid = UUID.randomUUID()
                          .toString();
        return commandGateway.sendAndWait(CreateAccountCommand.builder()
                                                              .uuid(uuid)
                                                              .name(accountDTO.getName())
                                                              .build());
    }

    @Override
    public AccountDTO updateSync(String uuid, AccountDTO accountDTO) {
        return commandGateway.sendAndWait(UpdateAccountCommand.builder()
                                                              .uuid(accountDTO.getUuid())
                                                              .name(accountDTO.getName())
                                                              .build());
    }

    @Override
    public void deleteSync(String uuid) {
        commandGateway.sendAndWait(DeleteAccountCommand.builder()
                                                       .uuid(uuid)
                                                       .build());
    }

    @Override
    public CompletableFuture<AccountDTO> creditSync(String uuid, double amount) {
        return commandGateway.send(CreditMoneyCommand.builder()
                                                           .uuid(uuid)
                                                           .amount(amount)
                                                           .build());
    }

    @Override
    public CompletableFuture<AccountDTO> debitSync(String uuid, double amount) {
        return commandGateway.send(DebitMoneyCommand.builder()
                                                    .uuid(uuid)
                                                    .amount(amount)
                                                    .build());
    }

}
