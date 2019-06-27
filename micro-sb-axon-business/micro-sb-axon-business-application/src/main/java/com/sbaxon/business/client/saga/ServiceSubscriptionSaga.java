package com.sbaxon.business.client.saga;

import com.sbaxon.business.account.command.CreateAccountCommand;
import com.sbaxon.business.account.event.AccountCreatedEvent;
import com.sbaxon.business.client.event.ServiceSubscribedEvent;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@Saga
public class ServiceSubscriptionSaga {

    private Logger log = LoggerFactory.getLogger(getClass());

    private CommandGateway commandGateway;

    //TODO: Axon utiliza el constructor por defecto, por eso no funciona la inyeccion de spring por constructor
    public ServiceSubscriptionSaga() {
    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "subscriptionUUID")
    public void on(ServiceSubscribedEvent serviceSubscribedEvent) {

        commandGateway.send(asCommandMessage(CreateAccountCommand.builder()
                                                                 .accountUUID(UUID.randomUUID()
                                                                                  .toString())
                                                                 .clientUUID(serviceSubscribedEvent.getClientUUID())
                                                                 .productUUID(serviceSubscribedEvent.getServiceUUID())
                                                                 .subscriptionUUID(serviceSubscribedEvent.getSubscriptionUUID())
                                                                 .build()), LoggingCallback.INSTANCE);

    }

    @SagaEventHandler(associationProperty = "subscriptionUUID")
    @EndSaga
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("AccountCreatedEvent: " + accountCreatedEvent.getAccountUUID());
    }
}
