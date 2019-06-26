package com.sbaxon.business.product;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventListener {

    private CommandGateway commandGateway;

    public ServiceEventListener(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

}
