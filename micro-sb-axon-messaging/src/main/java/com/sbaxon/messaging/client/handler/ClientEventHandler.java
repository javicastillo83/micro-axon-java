package com.sbaxon.messaging.client.handler;


import com.sbaxon.messaging.client.entity.Client;
import com.sbaxon.business.client.event.CreatedClientEvent;
import com.sbaxon.business.client.event.DeletedClientEvent;
import com.sbaxon.business.client.event.UpdatedClientEvent;
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
    public void on(CreatedClientEvent event) {
        clientRepository.save(new Client(event.getUuid(),event.getName()));
    }

    @EventHandler
    public void on(UpdatedClientEvent event) {
        Client client = clientRepository.findByUuid(event.getUuid());
        client.setName(event.getName());
        clientRepository.save(client);
    }

    @EventHandler
    public void on(DeletedClientEvent event) {
        clientRepository.deleteByUuid(event.getUuid());
    }

}
