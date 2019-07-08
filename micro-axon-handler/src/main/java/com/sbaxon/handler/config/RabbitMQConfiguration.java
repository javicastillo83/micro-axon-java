package com.sbaxon.handler.config;

import com.rabbitmq.client.Channel;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Exchange eventsExchange() {
        return ExchangeBuilder.topicExchange("events.tx").build();
    }

    @Bean
    public Queue eventsQueue() {
        return QueueBuilder.durable("events_queue").build();
    }

    @Bean
    public Binding eventBinding(){
        return BindingBuilder.bind(eventsQueue()).to(eventsExchange()).with("#").noargs();
    }

    @Bean
    public SpringAMQPMessageSource eventsMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {

            @RabbitListener(queues = "events_queue")
            @Override
            public void onMessage(Message message, Channel channel) {
                super.onMessage(message, channel);
            }
        };
    }

}
