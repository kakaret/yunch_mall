package com.study.rabbitmq;

import com.study.rabbitmq.config.RabbitConfiguration;
import com.study.rabbitmq.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void t1() {
//        send方法必须发送Message类型
        rabbitTemplate.send(new Message("Hello rabbit".getBytes(StandardCharsets.UTF_8)));
        rabbitTemplate.convertAndSend(new Object());
    }

    @Test
    public void t2() {
        String message = "订单已发货。 请注意查收~";
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.info", message);
    }
    @Test
    public void t3() throws InterruptedException {
        String message = "计划有变! 快跑!";
//        这个方法实现了消息可靠性投递中的confirm模式 它保证了消息至少能够成功的投递到交换机上
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("执行了confirm方法...");
                System.out.println(correlationData.getId());
                System.out.println(ack); //exchange接受消息成功返回true 失败返回false
                System.out.println(cause); //失败原因
                if(!ack) { //如果exchange返回ack==false 则重新发送
                    rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.info", message, new CorrelationData("110"));
                }
            }
        });
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.info", message, new CorrelationData("110"));
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void t4() {
        Person person = new Person(1, "宋江", "呼保义·及时雨");
//        spring会将Person对象序列化操作 将对象转换为字节数据
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "person.info", person);
    }

    @Test
    public void t5() throws InterruptedException {
        String message = "白日依山尽，黄河入海流";
        rabbitTemplate.setMandatory(true); //强制性要求消息的可靠性
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 如果雄安锡从exchange到queue的过程中出问题了 就自动回调这个方法
             * @param message 正在传输的信息
             * @param replyCode 返回状态码
             * @param replyText 返回的文本
             * @param exchange 交换机名称
             * @param routingKey 路由关键字
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("message: " + new String(message.getBody(), StandardCharsets.UTF_8));
                System.out.println("replyCode: "+ replyCode);
                System.out.println("replyText: "+ replyText);
                System.out.println("exchange: "+ exchange);
                System.out.println("routingKey: "+ routingKey);
            }
        });
//        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.info", message);
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.error", message);
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void t6() {
        for (int i = 0; i < 5; i++) {
            String message = " [%d] 订单已发货，请注意查收";
            rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, "order.info", String.format(message, i + 1));
        }
    }
}
