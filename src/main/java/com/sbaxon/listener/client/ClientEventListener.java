package com.sbaxon.listener.client;

import com.sbaxon.domain.client.service.CreateClient;
import com.sbaxon.domain.client.service.IClientService;
import com.sbaxon.domain.client.service.SubscribeProduct;
import com.sbaxon.domain.client.service.UpdateClient;
import com.sbaxon.listener.client.event.CreateClientEvent;
import com.sbaxon.listener.client.event.SubscribeProductEvent;
import com.sbaxon.listener.client.event.UnSubscribeProductEvent;
import com.sbaxon.listener.client.event.UpdateClientEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener {

    private IClientService clientService;

    public ClientEventListener(IClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = "create_clients_queue")
    public void createClient(CreateClientEvent createClientEvent) {
        clientService.create(CreateClient.builder()
                                         .lastName(createClientEvent.getLastName())
                                         .firstName(createClientEvent.getFirstName())
                                         .email(createClientEvent.getEmail())
                                         .build());
    }

    @RabbitListener(queues = "update_clients_queue")
    public void updateClient(UpdateClientEvent updateClientEvent) {
        clientService.update(updateClientEvent.getClientUUID(), UpdateClient.builder()
                                                                            .lastName(updateClientEvent.getLastName())
                                                                            .firstName(updateClientEvent.getFirstName())
                                                                            .email(updateClientEvent.getEmail())
                                                                            .build());
    }

    @RabbitListener(queues = "subscribe_product_queue")
    public void subscribeProduct(SubscribeProductEvent subscribeProductEvent) {
        clientService.subscribeProduct(subscribeProductEvent.getClientUUID(), SubscribeProduct.builder()
                                                                                              .bankServiceUUID(subscribeProductEvent.getBankServiceUUID())
                                                                                              .build());
    }

    @RabbitListener(queues = "unsubscribe_product_queue")
    public void unSubscribeProduct(UnSubscribeProductEvent unSubscribeProductEvent) {
        clientService.unSubscribeProduct(unSubscribeProductEvent.getClientUUID(), unSubscribeProductEvent.getBankServiceUUID());
    }

}
