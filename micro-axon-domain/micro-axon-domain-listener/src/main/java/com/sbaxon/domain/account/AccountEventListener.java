package com.sbaxon.domain.account;

import com.sbaxon.domain.account.event.CreditAccountEvent;
import com.sbaxon.domain.account.event.DebitAccountEvent;
import com.sbaxon.domain.account.service.IAccountService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEventListener {

    private IAccountService accountService;

    public AccountEventListener(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = "credit_queue")
    public void creditAccount(CreditAccountEvent creditAccountEvent) {
        accountService.credit(creditAccountEvent.getAccountUUID(), creditAccountEvent.getAmount());
    }

    @RabbitListener(queues = "debit_queue")
    public void debitAccount(DebitAccountEvent debitAccountEvent) {
        accountService.debit(debitAccountEvent.getAccountUUID(), debitAccountEvent.getAmount());

    }
}
