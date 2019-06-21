package com.sbaxon.business.account.aggregate;

import com.sbaxon.business.account.command.*;
import com.sbaxon.business.account.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String uuid;

    private String name;

    private double balance;


    public AccountAggregate() {
    }

    //Constructor en vez de un método porque axon lo usa en la creación
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        apply(CreatedAccountEvent.builder()
                                 .uuid(createAccountCommand.getUuid())
                                 .name(createAccountCommand.getName())
                                 .build());
    }

    @CommandHandler
    public void on(DeleteAccountCommand deleteAccountCommand) {
        apply(DeletedAccountEvent.builder()
                                 .uuid(deleteAccountCommand.getUuid())
                                 .build());
    }

    @CommandHandler
    public void on(UpdateAccountCommand updateAccountCommand) {
        apply(UpdatedAccountEvent.builder()
                                 .uuid(updateAccountCommand.getUuid())
                                 .name(updateAccountCommand.getName())
                                 .build());
    }

    @CommandHandler
    public void on(CreditMoneyCommand creditMoneyCommand) {
        apply(CreditMoneyEvent.builder()
                              .uuid(creditMoneyCommand.getUuid())
                              .amount(creditMoneyCommand.getAmount())
                              .build());
    }

    @CommandHandler
    public void on(DebitMoneyCommand debitMoneyCommand) {
        apply(DebitMoneyEvent.builder()
                             .uuid(debitMoneyCommand.getUuid())
                             .amount(debitMoneyCommand.getAmount())
                             .build());
    }


    @EventSourcingHandler
    public void on(CreatedAccountEvent createdAccountEvent) {
        uuid = createdAccountEvent.getUuid();
        name = createdAccountEvent.getName();
        balance = 0.0;
    }

    @EventSourcingHandler
    public void on(UpdatedAccountEvent updatedAccountEvent) {
        name = updatedAccountEvent.getName();
    }

    @EventSourcingHandler
    public void on(CreditMoneyEvent creditMoneyEvent) {
        balance += creditMoneyEvent.getAmount();
    }

    @EventSourcingHandler
    public void on(DebitMoneyEvent debitMoneyEvent) {
        balance -= debitMoneyEvent.getAmount();
    }
}
