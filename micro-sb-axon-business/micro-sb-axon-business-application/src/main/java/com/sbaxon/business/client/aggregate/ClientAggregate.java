package com.sbaxon.business.client.aggregate;

import com.sbaxon.business.client.command.CreateClientCommand;
import com.sbaxon.business.client.command.DeleteClientCommand;
import com.sbaxon.business.client.command.UpdateClientCommand;
import com.sbaxon.business.client.event.CreatedClientEvent;
import com.sbaxon.business.client.event.DeletedClientEvent;
import com.sbaxon.business.client.event.UpdatedClientEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ClientAggregate {

    @AggregateIdentifier
    private String uuid;

    private String name;

    public ClientAggregate() {
    }

    //Constructor en vez de un método porque axon lo usa en la creación
    @CommandHandler
    public ClientAggregate(CreateClientCommand createClientCommand) {
        apply(CreatedClientEvent.builder()
                                .uuid(createClientCommand.getUuid())
                                .name(createClientCommand.getName())
                                .build());
    }

    @CommandHandler
    public void on(DeleteClientCommand deleteClientCommand) {
        apply(DeletedClientEvent.builder()
                                .uuid(deleteClientCommand.getUuid())
                                .build());
    }

    @CommandHandler
    public void on(UpdateClientCommand updateClientCommand) {
        apply(UpdatedClientEvent.builder()
                                .uuid(updateClientCommand.getUuid())
                                .name(updateClientCommand.getName())
                                .build());
    }

    @EventSourcingHandler
    public void on(CreatedClientEvent createdClientEvent) {
        uuid = createdClientEvent.getUuid();
        name = createdClientEvent.getName();
    }


    @EventSourcingHandler
    public void on(UpdatedClientEvent updatedClientEvent) {
        name = updatedClientEvent.getName();
    }

}
