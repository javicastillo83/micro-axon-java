package com.sbaxon.messaging.account.handler;

import com.sbaxon.business.account.event.*;
import com.sbaxon.messaging.account.entity.Account;
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
    public void on(CreatedAccountEvent event) {
        accountRepository.save(new Account(event.getUuid(), event.getName()));
    }

    @EventHandler
    public void on(UpdatedAccountEvent event) {
        Account account = accountRepository.findByUuid(event.getUuid());
        account.setName(event.getName());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(DeletedAccountEvent event) {
        accountRepository.deleteByUuid(event.getUuid());
    }

    @EventHandler
    public void on(DebitMoneyEvent event) {
        Account account = accountRepository.findByUuid(event.getUuid());
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(CreditMoneyEvent event) {
        Account account = accountRepository.findByUuid(event.getUuid());
        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);
    }
}
