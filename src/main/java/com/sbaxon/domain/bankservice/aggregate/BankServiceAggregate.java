package com.sbaxon.domain.bankservice.aggregate;

import com.sbaxon.domain.bankservice.command.CreateBankServiceCommand;
import com.sbaxon.domain.bankservice.command.RemoveBankServiceCommand;
import com.sbaxon.domain.bankservice.command.UpdateBankServiceCommand;
import com.sbaxon.domain.bankservice.event.BankServiceCreatedEvent;
import com.sbaxon.domain.bankservice.event.BankServiceRemovedEvent;
import com.sbaxon.domain.bankservice.event.BankServiceUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

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

    @CommandHandler
    public void handle(UpdateBankServiceCommand updateBankServiceCommand) {
        apply(BankServiceUpdatedEvent.builder()
                                     .bankServiceUUID(updateBankServiceCommand.getBankServiceUUID())
                                     .bankServiceType(updateBankServiceCommand.getBankServiceType())
                                     .name(updateBankServiceCommand.getName())
                                     .build());
    }

    @EventSourcingHandler
    public void on(BankServiceUpdatedEvent bankServiceUpdatedEvent) {
        bankServiceUUID = bankServiceUpdatedEvent.getBankServiceUUID();
        name = bankServiceUpdatedEvent.getName();
        type = bankServiceUpdatedEvent.getBankServiceType();
    }

    @CommandHandler
    public void handle(RemoveBankServiceCommand removeBankServiceCommand) {
        apply(BankServiceRemovedEvent.builder()
                                     .bankServiceUUID(removeBankServiceCommand.getBankServiceUUID())
                                     .build());
    }

    @EventSourcingHandler
    public void on(BankServiceRemovedEvent bankServiceRemovedEvent) {
        markDeleted();
    }
}
