package com.sbaxon.listener.bankservice;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import com.sbaxon.domain.bankservice.service.CreateBankService;
import com.sbaxon.domain.bankservice.service.IBankServiceService;
import com.sbaxon.listener.bankservice.event.CreateBankServiceEvent;
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
