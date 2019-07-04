package com.sbaxon.domain.aggregate;

import com.sbaxon.domain.command.CreateBankServiceCommand;
import com.sbaxon.domain.event.BankServiceCreatedEvent;
import com.sbaxon.domain.event.BankServiceType;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class BankServiceAggregate {

    @AggregateIdentifier
    private String bankServiceUUID;

    private String name;

    private BankServiceType type;


    @CommandHandler
    public BankServiceAggregate(CreateBankServiceCommand createServiceCommand) {
        apply(BankServiceCreatedEvent.builder()
                                     .bankServiceUUID(createServiceCommand.getBankServiceUUID())
                                     .name(createServiceCommand.getName())
                                     .bankServiceType(createServiceCommand.getBankServiceType())
                                     .build());
    }


    @EventSourcingHandler
    public void on(BankServiceCreatedEvent bankServiceCreatedEvent) {
        bankServiceUUID = bankServiceCreatedEvent.getBankServiceUUID();
        name = bankServiceCreatedEvent.getName();
        type = bankServiceCreatedEvent.getBankServiceType();
    }
}
