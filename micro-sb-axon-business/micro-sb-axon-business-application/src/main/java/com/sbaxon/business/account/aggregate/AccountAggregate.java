package com.sbaxon.business.account.aggregate;

import com.sbaxon.business.account.command.CreateAccountCommand;
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

    private double balance;

    private AccountStatus status;

    @AggregateMember
    private List<Operation> operations;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        apply(AccountCreatedEvent.builder()
                                 .accountUUID(createAccountCommand.getAccountUUID())
                                 .clientUUID(createAccountCommand.getClientUUID())
                                 .number(UUID.randomUUID().toString())
                                 .subscriptionUUID(createAccountCommand.getSubscriptionUUID())
                                 .status(AccountStatus.CREATED)
                                 .balance(0.0)
                                 .build());
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        accountUUID = accountCreatedEvent.getAccountUUID();
        clientUUID = accountCreatedEvent.getClientUUID();
        number = accountCreatedEvent.getNumber();
        status = accountCreatedEvent.getStatus();
        balance = accountCreatedEvent.getBalance();
        operations = new ArrayList<>();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand creditMoneyCommand) {
        apply(MoneyCreditedEvent.builder()
                                .accountUUID(creditMoneyCommand.getAccountUUID())
                                .date(new Date())
                                .amount(creditMoneyCommand.getAmount())
                                .build());
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent moneyCreditedEvent) {

        if (this.balance < 0 & (this.balance + moneyCreditedEvent.getAmount()) >= 0) {
            apply(AccountActivatedEvent.builder()
                                       .accountUUID(this.accountUUID)
                                       .status(AccountStatus.ACTIVATED));
        }

        balance += moneyCreditedEvent.getAmount();

        //add operation
        operations.add(Operation.builder()
                                .operationUUID(UUID.randomUUID()
                                                   .toString())
                                .date(new Date())
                                .amount(moneyCreditedEvent.getAmount())
                                .build());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand debitMoneyCommand) {
        apply(MoneyDebitedEvent.builder()
                               .accountUUID(debitMoneyCommand.getAccountUUID())
                               .date(new Date())
                               .amount(debitMoneyCommand.getAmount())
                               .build());
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent moneyDebitedEvent) {

        if (this.balance >= 0 & (this.balance - moneyDebitedEvent.getAmount()) < 0) {
            apply(AccountDisabledEvent.builder()
                                      .accountUUID(this.accountUUID)
                                      .status(AccountStatus.HOLD));
        }
        balance -= moneyDebitedEvent.getAmount();

        //add operation
        operations.add(Operation.builder()
                                .operationUUID(UUID.randomUUID()
                                                   .toString())
                                .date(new Date())
                                .amount(moneyDebitedEvent.getAmount())
                                .build());

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
