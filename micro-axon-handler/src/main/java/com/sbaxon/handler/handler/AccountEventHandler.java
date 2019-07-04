package com.sbaxon.handler.handler;

import com.sbaxon.domain.event.AccountActivatedEvent;
import com.sbaxon.domain.event.AccountCreatedEvent;
import com.sbaxon.domain.event.MoneyCreditedEvent;
import com.sbaxon.domain.event.MoneyDebitedEvent;
import com.sbaxon.handler.entity.Account;
import com.sbaxon.handler.entity.AccountStatus;
import com.sbaxon.handler.repository.IAccountRepository;
import com.sbaxon.handler.repository.IClientRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("accounts")
public class AccountEventHandler {

    private final IAccountRepository accountRepository;

    private final IClientRepository clientRepository;

    public AccountEventHandler(IAccountRepository accountRepository, IClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        Account account = new Account();
        account.setClient(clientRepository.findByUuid(event.getClientUUID()));
        account.setNumber(event.getNumber());
        account.setBalance(event.getBalance());
        account.setStatus(AccountStatus.valueOf(event.getStatus().name()));
        account.setUuid(event.getAccountUUID());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        Account account = accountRepository.findByUuid(event.getAccountUUID());
        account.setStatus(AccountStatus.valueOf(event.getStatus().name()));
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
