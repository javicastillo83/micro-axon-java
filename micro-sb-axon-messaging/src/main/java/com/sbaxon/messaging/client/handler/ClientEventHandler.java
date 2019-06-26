package com.sbaxon.messaging.client.handler;

import com.sbaxon.business.client.event.ClientCreatedEvent;
import com.sbaxon.business.client.event.ClientUpdatedEvent;
import com.sbaxon.messaging.client.entity.Client;
import com.sbaxon.messaging.client.repository.IClientRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ClientEventHandler {

    private final IClientRepository clientRepository;

    public ClientEventHandler(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @EventHandler
    public void on(ClientCreatedEvent event) {
        Client client = new Client(event.getClientUUID(), event.getFirstName(), event.getLastName(), event.getEmail());
        clientRepository.save(client);
    }

    @EventHandler
    public void on(ClientUpdatedEvent event) {
        Client client = clientRepository.findByUuid(event.getClientUUID());
        client.setFirstName(event.getFirstName());
        client.setLastName(event.getLastName());
        client.setEmail(event.getEmail());
        clientRepository.save(client);
    }

}
