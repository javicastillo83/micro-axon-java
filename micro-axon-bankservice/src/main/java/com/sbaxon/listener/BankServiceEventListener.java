package com.sbaxon.listener;

import com.sbaxon.domain.event.BankServiceType;
import com.sbaxon.domain.service.CreateBankService;
import com.sbaxon.domain.service.IBankServiceService;
import com.sbaxon.listener.event.CreateBankServiceEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BankServiceEventListener {

    private IBankServiceService bankServiceService;

    public BankServiceEventListener(IBankServiceService bankServiceService) {
        this.bankServiceService = bankServiceService;
    }

    @RabbitListener(queues = "create_bankservice_queue")
    public void creditAccount(CreateBankServiceEvent createBankServiceEvent) {
        bankServiceService.create(CreateBankService.builder()
                                                   .bankServiceType(BankServiceType.valueOf(createBankServiceEvent.getBankServiceType()))
                                                   .name(createBankServiceEvent.getName())
                                                   .build());
    }

}
