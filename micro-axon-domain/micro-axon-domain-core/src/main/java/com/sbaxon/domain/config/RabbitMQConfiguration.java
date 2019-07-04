package com.sbaxon.domain.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    @Bean
    public Exchange eventsExchange() {
        return ExchangeBuilder.topicExchange("events.tx")
                              .build();
    }

    @Bean
    public Queue eventsQueue() {
        return QueueBuilder.durable("events_queue")
                           .build();
    }

    @Bean
    public Queue createBankServiceQueue() {
        return QueueBuilder.durable("create_bankservice_queue")
                           .build();
    }

    @Bean
    public Queue creditQueue() {
        return QueueBuilder.durable("credit_queue")
                           .build();
    }

    @Bean
    public Queue debitQueue() {
        return QueueBuilder.durable("debit_queue")
                           .build();
    }

    @Bean
    public Queue createClientsQueue() {
        return QueueBuilder.durable("create_clients_queue")
                           .build();
    }

    @Bean
    public Queue updateClientsQueue() {
        return QueueBuilder.durable("update_clients_queue")
                           .build();
    }

    @Bean
    public Queue subscribePoductsQueue() {
        return QueueBuilder.durable("subscribe_product_queue")
                           .build();
    }

    @Bean
    public Queue unSubscribePoductsQueue() {
        return QueueBuilder.durable("unsubscribe_product_queue")
                           .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(eventsQueue())
                             .to(eventsExchange())
                             .with("#")
                             .noargs();
    }

    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }
}
