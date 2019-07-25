package com.sbaxon.handler.handler;

import com.sbaxon.domain.bankservice.event.BankServiceCreatedEvent;
import com.sbaxon.domain.bankservice.event.BankServiceRemovedEvent;
import com.sbaxon.domain.bankservice.event.BankServiceUpdatedEvent;
import com.sbaxon.handler.entity.BankService;
import com.sbaxon.handler.repository.IBankServiceRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@ProcessingGroup("bankservices")
@Service
public class BankServiceEventHandler {

    private final IBankServiceRepository bankServiceRepository;
    private SimpMessageSendingOperations messagingTemplate;

    public BankServiceEventHandler(IBankServiceRepository bankServiceRepository,SimpMessageSendingOperations messagingTemplate) {
        this.bankServiceRepository = bankServiceRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @EventHandler
    public void on(BankServiceCreatedEvent bankServiceCreatedEvent){
        BankService bankService = new BankService();
        bankService.setName(bankServiceCreatedEvent.getName());
        bankService.setUuid(bankServiceCreatedEvent.getBankServiceUUID());
        bankService.setType(bankServiceCreatedEvent.getBankServiceType());
        bankServiceRepository.save(bankService);

        broadcastUpdates();
    }

    @EventHandler
    public void on(BankServiceUpdatedEvent event) {
        BankService bankService = bankServiceRepository.findByUuid(event.getBankServiceUUID());
        bankService.setName(event.getName());
        bankService.setType(event.getBankServiceType());
        bankServiceRepository.save(bankService);
        broadcastUpdates();
    }

    @EventHandler
    public void on(BankServiceRemovedEvent event) {
        bankServiceRepository.removeByUuid(event.getBankServiceUUID());
        broadcastUpdates();
    }

    private void broadcastUpdates() {
        List<BankService> bankServices = bankServiceRepository.findAll();
        messagingTemplate.convertAndSend("/topic/bankservices.updates", bankServices);
    }
}
