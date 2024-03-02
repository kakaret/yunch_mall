package com.study.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//专门用来发布消息的类
public class MessageSender {
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

    //    1. 测试普通（队列）消息
    @Test
    public void t1() throws IOException, TimeoutException {
//         创建一个频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
//        声明一个队列
        /*
        参数1：队列名称 参数2：是否为持久化队列 参数3：是否独占当前连接 参数4：是否在使用完毕后自动删除 参数5：其他额外参数
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        声明消息内容
        String message = "Hello, Rabbit!";
//        发布消息
        /*
        参数1：交换机名称，如果没有指定则使用默认
        参数2：路由key 如果是简单模式则为队列名称
        参数3：消息的其他属性，可省略
        参数4：消息内容(字节数组)
         */
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息已投递到RabbitMQ：" + message);
//        关闭资源
        channel.close();
        conn.close();
    }

    @Test
    public void t2() throws IOException, TimeoutException, InterruptedException {
//         创建一个频道
        Channel channel = conn.createChannel();
        String queueName = "simple_queue";
//        声明一个队列
        /*
        参数1：队列名称 参数2：是否为持久化队列 参数3：是否独占当前连接 参数4：是否在使用完毕后自动删除 参数5：其他额外参数
         */
        channel.queueDeclare(queueName, true, false, false, null);
//        声明消息内容
        for (int i = 0; i < 30; i++) {
            String message = "测试消息" + i;
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息已投递到RabbitMQ：" + message);
            TimeUnit.SECONDS.sleep(1);
        }
//        关闭资源
        channel.close();
        conn.close();
    }

    //    使用发布订阅模式
    @Test
    public void t3() throws IOException, TimeoutException {
        Channel channel = conn.createChannel();
//        声明交换机
        String exchangeName = "exchange_fanout";
        /**
         * 声明一个交换机
         * 参数1：交换机名称 参数2：交换机类型 参数3：是否持久化消息 参数4：是否自动删除 参数5：是否为内部使用 参数6：其他额外参数
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true, false, false, null);

//        声明多个队列
        String queue1 = "queue_fanout1", queue2 = "queue_fanout2";
        channel.queueDeclare(queue1, true, false, false, null);
        channel.queueDeclare(queue2, true, false, false, null);
//        声明一个Fanout消息
        String message = "测试Fanout消息(广播模式)，粉刷本领强!";
        /**
         * 绑定队列和交换机
         * 参数1：队列名 参数2：交换机 参数3：绑定规则
         */
        channel.queueBind(queue1, exchangeName, "");
        channel.queueBind(queue2, exchangeName, "");
//        发布消息
        channel.basicPublish(exchangeName, "", null, message.getBytes(StandardCharsets.UTF_8));
//        释放资源
        channel.close();
        conn.close();
    }

    //    4 使用routing key绑定队列和交换机 交换机将通过消息的routing key来决定消息绑定在那个队列上
    @Test
    public void t4() throws IOException, TimeoutException {
        Channel channel = conn.createChannel();
        String exchange = "exchange_direct"; //声明交换机
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT, true, false, false, null);
        String queue1 = "queue_direct1", queue2 = "queue_direct2"; //声明两个队列
        channel.queueDeclare(queue1, true, false, false, null);
        channel.queueDeclare(queue2, true, false, false, null);
//        绑定队列
        channel.queueBind(queue1, exchange, "sport"); //队列1保存体育消息
        channel.queueBind(queue2, exchange, "traffic"); //队列2保存交通消息
        channel.queueBind(queue2, exchange, "life"); //队列2保存生活消息
//        分别声明3种消息
        String m1 = "消息1：两只老虎跑得快", m2 = "消息2：小火车的轮子转呀转呀转", m3 = "消息3：小呀么小二郎，背着书包上学堂";
//        发送消息：此时不在直接将消息发布到某个队列，而是由交换机来通过routing key 来判断由那个队列来保存消息
        channel.basicPublish(exchange, "sport", null, m1.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "traffic", null, m2.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "life", null, m3.getBytes(StandardCharsets.UTF_8));
//        释放资源
        channel.close();
        conn.close();
    }

    //    5 主题模式 或者叫模糊匹配模式
    @Test
    public void t5() throws IOException, TimeoutException {
        Channel channel = conn.createChannel();
        String exchange = "exchange_topic";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC, true, false, false, null);
//         声明队列
        String queue1 = "queue_topic1", queue2 = "queue_topic2";
        channel.queueDeclare(queue1, true, false, false, null);
        channel.queueDeclare(queue2, true, false, false, null);
//         绑定队列 不在具体的绑定某个routing key，而是和具体的topic绑定
        channel.queueBind(queue1, exchange, "libai.*");//只要是李白的诗都保存到队列1
        channel.queueBind(queue2, exchange, "*.*.view");
        channel.queueBind(queue2, exchange, "#.#.friend");
        String m1 = "日照香炉生紫烟，遥看瀑布挂前川";
        String m2 = "桃花潭水深千尺，不及汪伦送我情";
        String m3 = "会当凌绝顶，一览众山小";
        String m4 = "莫愁前路无知己，天下谁人不识君";
        channel.basicPublish(exchange, "libai.view", null, m1.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "libai.friend", null, m2.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "tang.dufu.view", null, m3.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "tang.gaoshi.friend", null, m4.getBytes(StandardCharsets.UTF_8));
//        释放资源
        channel.close();
        conn.close();
    }
}
