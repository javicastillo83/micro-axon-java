package com.sbaxon.messaging.service.handler;

import com.sbaxon.business.service.event.ServiceCreatedEvent;
import com.sbaxon.messaging.service.entity.Service;
import com.sbaxon.messaging.service.repository.IServiceRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventHandler {

    private final IServiceRepository serviceRepository;

    public ServiceEventHandler(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @EventHandler
    public void on(ServiceCreatedEvent serviceCreatedEvent){
        Service service = new Service();
        service.setName(serviceCreatedEvent.getName());
        service.setUuid(serviceCreatedEvent.getServiceUUID());
        service.setType(serviceCreatedEvent.getServiceType());
        serviceRepository.save(service);
    }
}
