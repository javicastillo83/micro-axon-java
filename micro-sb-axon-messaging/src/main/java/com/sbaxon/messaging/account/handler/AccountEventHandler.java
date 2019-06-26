package com.sbaxon.messaging.account.handler;

import com.sbaxon.business.account.event.AccountActivatedEvent;
import com.sbaxon.business.account.event.AccountCreatedEvent;
import com.sbaxon.business.account.event.MoneyCreditedEvent;
import com.sbaxon.business.account.event.MoneyDebitedEvent;
import com.sbaxon.messaging.account.entity.Account;
import com.sbaxon.messaging.account.entity.AccountStatus;
import com.sbaxon.messaging.account.repository.IAccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {

    private final IAccountRepository accountRepository;

    public AccountEventHandler(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        Account account = new Account(event.getAccountUUID(), event.getClientUUID());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        Account account = accountRepository.findByUuid(event.getAccountUUID());
        account.setStatus(AccountStatus.valueOf(event.getStatus().toString()));
        accountRepository.save(account);
    }

    @EventHandler
    public void on(MoneyDebitedEvent event) {
        Account account = accountRepository.findByUuid(event.getAccountUUID());
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) {
        Account account = accountRepository.findByUuid(event.getAccountUUID());
        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);
    }
}
