package com.sbaxon.business.client.listener;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener {

    private CommandGateway commandGateway;

    public ClientEventListener(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

   /* @RabbitListener
    public void createAccount(ClientEvent clientEvent) {
        commandGateway.send(CreateAccountCommand.builder()
                                                .uuid(UUID.randomUUID().toString())
                                                .name(clientEvent.getName())
                                                .build());
    }*/
}
