package com.study.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
//    分别声明交换机名称和队列名称
    public static final String EXCHANGE_NAME = "normal_exchange";
    public static final String QUEUE_NAME = "normal_queue";
    public static final String DLX_EXCHANGE_NAME = "dlx_exchange";
    public static final String DLX_QUEUE_NAME = "dlx_queue";

//    创建死信交换机
    @Bean("dlxExchange")
    public Exchange dlxExchange() {
         return ExchangeBuilder.topicExchange(DLX_EXCHANGE_NAME).durable(true).build();
     }

//     创建死信队列
     @Bean("dlxQueue")
     public Queue dlxQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
     }

//     将死信交换机和死信队列绑定在一起
    @Bean
    public Binding bindingDlx(@Qualifier("dlxQueue") Queue queue, @Qualifier("dlxExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dlx.*").noargs();
    }

//    依次配置交换机、队列、绑定器对应的Bean
    @Bean("normalExchange")
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean("normalQueue")
    public Queue queue() {
        return QueueBuilder
                .durable(QUEUE_NAME) //创建持久化队列
                .expires(24 * 60 * 60 * 1000) // 设置队列的超时销毁时间 如果队列在连续的50000ms都没有存储信息 该队列被delete 保证资源及时回收
                .ttl(30 * 60 * 1000) // 设置消息的超时时间 如果消息在1000ms内没有被消费 该消息则会被删除或写入死信队列（如果设置了死信队列）
                .deadLetterExchange("死信消息交换机名称") //超时的消息不会被直接删除 而是写入死信队列
                .deadLetterExchange("dlxExchange")
                .deadLetterRoutingKey("dlx.info")
                .build();
    }
    @Bean
    public Binding binding(@Qualifier("normalExchange") Exchange exchange,
                           @Qualifier("normalQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("*.info").noargs();
    }
}
