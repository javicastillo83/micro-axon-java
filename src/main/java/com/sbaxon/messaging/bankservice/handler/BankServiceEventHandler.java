package com.sbaxon.messaging.bankservice.handler;

import com.sbaxon.domain.bankservice.event.BankServiceCreatedEvent;
import com.sbaxon.messaging.bankservice.entity.BankService;
import com.sbaxon.messaging.bankservice.repository.IBankServiceRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@ProcessingGroup("bankservices")
@Service
public class BankServiceEventHandler {

    private final IBankServiceRepository serviceRepository;

    public BankServiceEventHandler(IBankServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @EventHandler
    public void on(BankServiceCreatedEvent bankServiceCreatedEvent){
        BankService bankService = new BankService();
        bankService.setName(bankServiceCreatedEvent.getName());
        bankService.setUuid(bankServiceCreatedEvent.getBankServiceUUID());
        bankService.setType(bankServiceCreatedEvent.getBankServiceType());
        serviceRepository.save(bankService);
    }
}
