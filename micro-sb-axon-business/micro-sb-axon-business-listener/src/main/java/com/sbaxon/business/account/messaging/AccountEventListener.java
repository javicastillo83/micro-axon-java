package com.sbaxon.business.account.messaging;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class AccountEventListener {

    private CommandGateway commandGateway;

    public AccountEventListener(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
/*
    @RabbitListener
    public void createAccount(AccountEvent accountEvent) {
        commandGateway.send(CreateAccountCommand.builder()
                                                .uuid(UUID.randomUUID().toString())
                                                .name(accountEvent.getName())
                                                .build());
    }*/
}
