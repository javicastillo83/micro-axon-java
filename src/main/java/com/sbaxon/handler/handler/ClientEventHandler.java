package com.sbaxon.handler.handler;

import com.sbaxon.domain.client.event.ClientCreatedEvent;
import com.sbaxon.domain.client.event.ClientRemovedEvent;
import com.sbaxon.domain.client.event.ClientUpdatedEvent;
import com.sbaxon.handler.entity.Client;
import com.sbaxon.handler.repository.IClientRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ProcessingGroup("clients")
public class ClientEventHandler {

    private final IClientRepository clientRepository;

    private SimpMessageSendingOperations messagingTemplate;

    public ClientEventHandler(IClientRepository clientRepository, SimpMessageSendingOperations messagingTemplate) {
        this.clientRepository = clientRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @EventHandler
    public void on(ClientCreatedEvent event) {
        Client client = new Client(event.getClientUUID(), event.getFirstName(), event.getLastName(), event.getEmail());
        clientRepository.save(client);
        broadcastUpdates();
    }

    @EventHandler
    public void on(ClientUpdatedEvent event) {
        Client client = clientRepository.findByUuid(event.getClientUUID());
        client.setFirstName(event.getFirstName());
        client.setLastName(event.getLastName());
        client.setEmail(event.getEmail());
        clientRepository.save(client);
        broadcastUpdates();
    }

    @EventHandler
    public void on(ClientRemovedEvent event) {
        clientRepository.removeByUuid(event.getClientUUID());
        broadcastUpdates();
    }

    private void broadcastUpdates() {
        List<Client> clients = clientRepository.findAll();
        messagingTemplate.convertAndSend("/topic/client.updates", clients);
    }

}
