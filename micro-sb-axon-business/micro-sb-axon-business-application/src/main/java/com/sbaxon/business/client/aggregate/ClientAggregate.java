package com.sbaxon.business.client.aggregate;

import com.sbaxon.business.client.command.CreateClientCommand;
import com.sbaxon.business.client.command.SubscribeServiceCommand;
import com.sbaxon.business.client.command.UnSubscribeServiceCommand;
import com.sbaxon.business.client.command.UpdateClientCommand;
import com.sbaxon.business.client.event.ClientCreatedEvent;
import com.sbaxon.business.client.event.ClientUpdatedEvent;
import com.sbaxon.business.client.event.ServiceSubscribedEvent;
import com.sbaxon.business.client.event.ServiceUnSubscribedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ClientAggregate {

    @AggregateIdentifier
    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;

    @AggregateMember
    private List<ProductEntity> products;


    public ClientAggregate() {
    }

    @CommandHandler
    public ClientAggregate(CreateClientCommand createClientCommand) {
        apply(ClientCreatedEvent.builder()
                                .clientUUID(createClientCommand.getClientUUID())
                                .firstName(createClientCommand.getFirstName())
                                .lastName(createClientCommand.getLastName())
                                .email(createClientCommand.getEmail())
                                .build());
    }

    @EventSourcingHandler
    public void on(ClientCreatedEvent clientCreatedEvent) {
        clientUUID = clientCreatedEvent.getClientUUID();
        firstName = clientCreatedEvent.getFirstName();
        lastName = clientCreatedEvent.getLastName();
        email = clientCreatedEvent.getEmail();
        products = new ArrayList<>();
    }

    @CommandHandler
    public void handle(UpdateClientCommand updateClientCommand) {
        apply(ClientUpdatedEvent.builder()
                                .clientUUID(updateClientCommand.getClientUUID())
                                .firstName(updateClientCommand.getFirstName())
                                .lastName(updateClientCommand.getLastName())
                                .email(updateClientCommand.getEmail())
                                .build());
    }

    @EventSourcingHandler
    public void on(ClientUpdatedEvent clientUpdatedEvent) {
        clientUUID = clientUpdatedEvent.getClientUUID();
        firstName = clientUpdatedEvent.getFirstName();
        lastName = clientUpdatedEvent.getLastName();
        email = clientUpdatedEvent.getEmail();
    }

    @CommandHandler
    public void handle(SubscribeServiceCommand subscribeServiceCommand) {
        apply(ServiceSubscribedEvent.builder()
                                    .serviceUUID(subscribeServiceCommand.getServiceUUID())
                                    .clientUUID(subscribeServiceCommand.getClientUUID())
                                    .subscriptionUUID(UUID.randomUUID().toString())
                                    .build());
    }

    @EventSourcingHandler
    public void on(ServiceSubscribedEvent serviceSubscribedEvent) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductUUID(UUID.randomUUID().toString());
        productEntity.setServiceUUID(serviceSubscribedEvent.getServiceUUID());
        products.add(productEntity);
    }

    @CommandHandler
    public void handle(UnSubscribeServiceCommand unSubscribeServiceCommand) {
        apply(ServiceUnSubscribedEvent.builder()
                                      .productUUID(unSubscribeServiceCommand.getProductUUID())
                                      .build());
    }

    @EventSourcingHandler
    public void on(ServiceUnSubscribedEvent serviceUnSubscribedEvent) {
        products.removeIf(s -> s.getProductUUID().equalsIgnoreCase(serviceUnSubscribedEvent.getProductUUID()));
    }
}
