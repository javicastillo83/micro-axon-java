package com.sbaxon.handler.handler;

import com.sbaxon.domain.account.event.AccountActivatedEvent;
import com.sbaxon.domain.account.event.AccountCreatedEvent;
import com.sbaxon.domain.account.event.MoneyCreditedEvent;
import com.sbaxon.domain.account.event.MoneyDebitedEvent;
import com.sbaxon.handler.entity.Account;
import com.sbaxon.handler.entity.AccountStatus;
import com.sbaxon.handler.repository.IAccountRepository;
import com.sbaxon.handler.repository.IClientRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("accounts")
public class AccountEventHandler {

    private final IAccountRepository accountRepository;

    private final IClientRepository clientRepository;

    private SimpMessageSendingOperations messagingTemplate;

    public AccountEventHandler(IAccountRepository accountRepository, IClientRepository clientRepository, SimpMessageSendingOperations messagingTemplate) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
        Account account = new Account();
        account.setClient(clientRepository.findByUuid(event.getClientUUID()));
        account.setNumber(event.getNumber());
        account.setBalance(event.getBalance());
        account.setStatus(AccountStatus.valueOf(event.getStatus()
                                                     .name()));
        account.setUuid(event.getAccountUUID());
        accountRepository.save(account);

        broadcastUpdates(account.getClient()
                                .getId());
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        Account account = accountRepository.findByUuid(event.getAccountUUID());
        account.setStatus(AccountStatus.valueOf(event.getStatus()
                                                     .name()));
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

    private void broadcastUpdates(Long clientId) {
        List<AccountM> bankServices = accountRepository.findByClientId(clientId)
                                                      .stream()
                                                      .map( account -> {//guarrada para evitar infinite recursion
                                                          AccountM m = new AccountM();
                                                          BeanUtils.copyProperties(account, m);
                                                          return m;
                                                      }).collect(Collectors.toList());

        messagingTemplate.convertAndSend("/topic/account.updates", bankServices);
    }
}
