package com.sbaxon.business.client.listener;

import com.sbaxon.business.account.command.CreateAccountCommand;
import com.sbaxon.business.client.event.ClientEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientEventListener {

    private CommandGateway commandGateway;

    public ClientEventListener(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RabbitListener
    public void createAccount(ClientEvent clientEvent) {
        commandGateway.send(CreateAccountCommand.builder()
                                                .uuid(UUID.randomUUID().toString())
                                                .name(clientEvent.getName())
                                                .build());
    }
}
