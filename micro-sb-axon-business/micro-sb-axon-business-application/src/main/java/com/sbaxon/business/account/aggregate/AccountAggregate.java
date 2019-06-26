package com.sbaxon.business.account.aggregate;

import com.sbaxon.business.account.command.CreditMoneyCommand;
import com.sbaxon.business.account.command.DebitMoneyCommand;
import com.sbaxon.business.account.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountUUID;

    private String clientUUID;

    private String number;

    private double amount;

    private AccountStatus status;

    @AggregateMember
    private List<Operation> operations;

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        number = accountCreatedEvent.getNumber();
        clientUUID = accountCreatedEvent.getClientUUID();
        status = AccountStatus.CREATED;
        operations = new ArrayList<>();
    }

    @CommandHandler
    public void on(CreditMoneyCommand creditMoneyCommand) {
        apply(MoneyCreditedEvent.builder()
                                .accountUUID(creditMoneyCommand.getAccountUUID())
                                .date(new Date())
                                .amount(creditMoneyCommand.getAmount())
                                .build());
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent moneyCreditedEvent) {

        if (this.amount < 0 & (this.amount + moneyCreditedEvent.getAmount()) >= 0) {
            apply(AccountActivatedEvent.builder()
                                       .accountUUID(this.accountUUID)
                                       .status(AccountStatus.ACTIVATED));
        }

        amount += moneyCreditedEvent.getAmount();

        //add operation
        operations.add(Operation.builder()
                                .operationUUID(UUID.randomUUID().toString())
                                .date(new Date())
                                .amount(moneyCreditedEvent.getAmount()).build());
    }

    @CommandHandler
    public void on(DebitMoneyCommand debitMoneyCommand) {
        apply(MoneyDebitedEvent.builder()
                               .accountUUID(debitMoneyCommand.getAccountUUID())
                               .date(new Date())
                               .amount(debitMoneyCommand.getAmount())
                               .build());
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent moneyDebitedEvent) {

        if (this.amount >= 0 & (this.amount - moneyDebitedEvent.getAmount()) < 0) {
            apply(AccountDisabledEvent.builder()
                                      .accountUUID(this.accountUUID)
                                      .status(AccountStatus.HOLD));
        }
        amount -= moneyDebitedEvent.getAmount();

        //add operation
        operations.add(Operation.builder()
                                .operationUUID(UUID.randomUUID().toString())
                                .date(new Date())
                                .amount(moneyDebitedEvent.getAmount()).build());

    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        status = accountActivatedEvent.getStatus();

    }

    @EventSourcingHandler
    public void on(AccountDisabledEvent accountDisabledEvent) {
        status = accountDisabledEvent.getStatus();
    }

}
