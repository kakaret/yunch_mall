package com.zrrd.yunchmall.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "mall_exchange";
    public final static String QUEUE_NAME = "mall_queue";
    @Bean("mallExchange")
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    @Bean("mallQueue")
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    @Bean
    public Binding binding(@Qualifier("mallExchange") Exchange exchange,
                           @Qualifier("mallQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("mall.*").noargs();
    }
}
