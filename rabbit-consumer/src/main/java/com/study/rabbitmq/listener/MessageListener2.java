package com.study.rabbitmq.listener;

import com.study.rabbitmq.entity.Person;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

//@Component
@RabbitListener(queues = "normal_queue", ackMode = "MANUAL")
public class MessageListener2 {
//    如果一个监听器类中存在u东哥消息处理的方法的化 需要将其中一个设置为 default
    @RabbitHandler(isDefault = true)
//    当接受到消息时 程序进行了一个反序列化的操作 将消息内容重新转化为java对象
    public void process(Person person) {
        System.out.println(" [Y1] 接收到消息并处理~");
        System.out.println("消息内容：" + person);
    }

    @RabbitHandler
    public void process2(Person person) {
        System.out.println(" [Y2] 接收到消息并处理~");
        System.out.println("消息内容：" + person);
    }
}
