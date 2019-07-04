package com.sbaxon.domain.aggregate;

import com.sbaxon.domain.command.CreateClientCommand;
import com.sbaxon.domain.command.SubscribeServiceCommand;
import com.sbaxon.domain.command.UnSubscribeServiceCommand;
import com.sbaxon.domain.command.UpdateClientCommand;
import com.sbaxon.domain.event.ClientCreatedEvent;
import com.sbaxon.domain.event.ClientUpdatedEvent;
import com.sbaxon.domain.event.ServiceSubscribedEvent;
import com.sbaxon.domain.event.ServiceUnSubscribedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition = "snapshotter")
@NoArgsConstructor //Axon
@Data
public class ClientAggregate  {

    @AggregateIdentifier
    private String clientUUID;

    //Value Object
    private ClientInfo clientInfo;

    //Entity List
    @AggregateMember
    private List<ProductEntity> products;


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
        clientInfo = ClientInfo.builder()
                               .firstName(clientCreatedEvent.getFirstName())
                               .lastName(clientCreatedEvent.getLastName())
                               .email(clientCreatedEvent.getEmail())
                               .build();
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
        clientInfo = ClientInfo.builder()
                              .firstName(clientUpdatedEvent.getFirstName())
                              .lastName(clientUpdatedEvent.getLastName())
                              .email(clientUpdatedEvent.getEmail())
                              .build();
    }

    @CommandHandler
    public void handle(SubscribeServiceCommand subscribeServiceCommand) {
        apply(ServiceSubscribedEvent.builder()
                                    .bankServiceUUID(subscribeServiceCommand.getBankServiceUUID())
                                    .clientUUID(subscribeServiceCommand.getClientUUID())
                                    .subscriptionUUID(UUID.randomUUID()
                                                          .toString())
                                    .build());
    }

    @EventSourcingHandler
    public void on(ServiceSubscribedEvent serviceSubscribedEvent) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductUUID(UUID.randomUUID()
                                         .toString());
        productEntity.setBankServiceUUID(serviceSubscribedEvent.getBankServiceUUID());
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
        products.removeIf(s -> s.getProductUUID()
                                .equalsIgnoreCase(serviceUnSubscribedEvent.getProductUUID()));
    }
}
