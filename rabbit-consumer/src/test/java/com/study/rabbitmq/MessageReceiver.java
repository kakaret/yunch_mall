package com.study.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MessageReceiver {
    //    用于连接RabbitMQ Server 的实例
    private Connection conn;
    @Before
    public void init() throws IOException, TimeoutException {
//        1. 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
//        2. 完成工厂的基本信息注册
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672); //rabbitmq的端口
        connectionFactory.setUsername("yunchmall");
        connectionFactory.setPassword("yunchmall");
        connectionFactory.setVirtualHost("/mqtest");
//        3. 创建一个连接
        conn = connectionFactory.newConnection();
    }

    @Test
    public void t0() {
        System.out.println("输出连接信息: " + conn);
    }

//    普通模式 消费消息
    @Test
    public void t1() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("1号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("1号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t2() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("2号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("2号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t3() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("3号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("3号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t4() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("4号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("4号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

//    接收广播消息
    @Test
    public void t5() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "queue_fanout1";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("5号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("5号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t6() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "queue_fanout2";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("6号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("6号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

//    路由模式接收消息
    @Test
    public void t7() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "queue_direct1";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("7号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("7号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t8() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "queue_direct2";
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("8号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("8号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }

    @Test
    public void t9() throws IOException, InterruptedException {
//         1.创建频道
        Channel channel = conn.createChannel();
        String queueName = "queue_topic1";
//        声明交换机
        String exchange = "exchange_topic";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC, true, false, false, null);
        /**
         * 声明当前消费这从哪一个队列接收消息
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        绑定队列 同时设置关注的主题（这样做的目的是可以对当前队列保存的消息进行过滤）
        channel.queueBind(queueName, exchange, "*.view");
//        创建一个消费者实例 重写处理消息的方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("9号消费者接收到消息：" + new String(body, StandardCharsets.UTF_8));
                System.out.println("消息主题：" + envelope.getRoutingKey());
                System.out.println("交换机名称：" + envelope.getExchange());
            }
        };
        /**
         * 监听队列 保证第一时间得到消息
         * 参数1：队列名称 参数2：是否自动确认 true表示消息接收到自动项MQ回复确认信息 MQ将会删除这条消息 参数3：处理这条消息的消费者实例
         */
        channel.basicConsume(queueName, true, consumer);
        System.out.println("9号消费者已就绪！");
        TimeUnit.MINUTES.sleep(5); //睡眠1分钟
    }
}
