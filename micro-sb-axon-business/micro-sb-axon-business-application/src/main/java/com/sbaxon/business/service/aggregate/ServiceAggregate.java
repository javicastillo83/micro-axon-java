package com.sbaxon.business.service.aggregate;

import com.sbaxon.business.service.command.CreateServiceCommand;
import com.sbaxon.business.service.event.ServiceCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ServiceAggregate {

    @AggregateIdentifier
    private String serviceUUID;

    private String name;

    private ServiceType type;


    @CommandHandler
    public ServiceAggregate(CreateServiceCommand createServiceCommand) {
        apply(ServiceCreatedEvent.builder()
                                 .serviceUUID(createServiceCommand.getServiceUUID())
                                 .name(createServiceCommand.getName())
                                 .serviceType(createServiceCommand.getServiceType())
                                 .build());
    }


    @EventSourcingHandler
    public void on(ServiceCreatedEvent serviceCreatedEvent) {
        serviceUUID = serviceCreatedEvent.getServiceUUID();
        name = serviceCreatedEvent.getName();
        type = serviceCreatedEvent.getServiceType();
    }
}
