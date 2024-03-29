package com.sbaxon.handler.handler;

import com.sbaxon.domain.event.ClientCreatedEvent;
import com.sbaxon.domain.event.ClientUpdatedEvent;
import com.sbaxon.handler.entity.Client;
import com.sbaxon.handler.repository.IClientRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("clients")
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
