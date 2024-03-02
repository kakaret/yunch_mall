package com.study.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageListener implements ChannelAwareMessageListener {

//    自动装配一个连接工厂
    private ConnectionFactory connectionFactory;

    @Bean(name = "containerFactory")
    public SimpleRabbitListenerContainerFactory mqConsumerlistenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory); //注入连接工厂
//        谁知消息的抓取数量（流量控制） 当消息的高峰期，消息会激增涌入消费端系统或服务导致整体的服务状态下降或宕机
//        流量控制的意义在于即使有再多的消息
        factory.setPrefetchCount(1); //设置消息的抓取数量（流量控制）
        return factory;
    }

//    onMessage方法会在接收到消息时自动回调
    @Override
//    监听1个或多个队列
    @RabbitListener(queues = {"normal_queue"})
    public void onMessage(Message message, Channel channel) throws Exception {
//        String info = " [X] 接收到消息：频道[%d], 消息内容[%s]";
//        System.out.println(String.format(info, channel.getChannelNumber(), new String(message.getBody(), StandardCharsets.UTF_8)));
        Random random = new Random();
        boolean x = random.nextBoolean();
        try {
            if (x) {
                System.out.println("消息处理成功/.");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            } else {
                System.out.println("模拟程序执行出错了, 消息处理失败, 需要重新发送");
                throw new RuntimeException("程序出错了");
            }
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }
    }

}
