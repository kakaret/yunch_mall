package com.zrrd.yunchmall.study.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest1 {

//     专门用于在java程序里访问redis服务
    private Jedis jedis;

    @Before
     public void init() {
//         注意 如果使用docker启动的redis ip要换成你的虚拟机ip
         jedis = new Jedis("47.76.43.22", 6379);
//         jedis.auth("root");
         jedis.select(0);
    }

    @Test
     public void testPing() {
         String msg = jedis.ping("hello redis");
         System.out.println(msg);
    }
//    测试String类型
    @Test
    public void testString() {
        String msg = jedis.set("msg", "你是一个好人~");
        jedis.append("msg", "但是，我们不合适!");
//        30s后这个key被移除
        jedis.expire("msg", 30);
//        判断key是否存在
        if(jedis.exists("msg")) {
            String val = jedis.get("msg");
            System.out.println(val);
        }
        System.out.println("OK");
    }

    @Test
    public void testList() {
        jedis.lpush("cities", "纽约");
        jedis.lpush("cities", "绥化");

        jedis.rpush("cities", "伦敦");
        jedis.rpush("cities", "佳木斯");
        System.out.println("列表cities的长度：" + jedis.llen("cities"));
        List<String> cities = jedis.lrange("cities", 0, -1);
        System.out.println(cities);
    }

    @Test
    public void testSet() {
        jedis.sadd("fruits", "香蕉", "苹果", "西瓜", "菠萝");
        jedis.sadd("fruits", "苹果");
        long n = jedis.scard("fruits");
        System.out.println("集合fruits的元素个数："+ n);
        jedis.smembers("fruits").forEach(System.out::println);
//        jedis.smembers("fruits").forEach(item -> System.out.println(item));
    }

    @Test
    public void testZSet() {
        jedis.zadd("scoreOfLfh", 46, "数据结构");
        jedis.zadd("scoreOfLfh", 60, "计算机组成原理");
        jedis.zadd("scoreOfLfh", 78, "大学英语");
        jedis.zadd("scoreOfLfh", 60, "复变函数");
//        取出当前key对应的zset一共有几个元素
        long total = jedis.zcard("scoreOfLfh");
        System.out.println("当前key对应的zset一共有几个元素：" + total);
        long n = jedis.zcount("scoreOfLfh", 0, 70);
        System.out.println("分数在0到70之间的元素个数："+ n);
        jedis.zrange("scoreOfLfh", 0, -1).forEach(System.out::println);
    }

    @Test
    public void testHash() {
        jedis.hset("user", "name", "李飞鸿");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "男");
        if(jedis.hexists("user","name")) {
            jedis.hset("user", "name", "李飞鸿");
        }
//        如果对应某个filed未设置 则设置对应的value
        jedis.hsetnx("user", "star", "金牛座");
        jedis.hsetnx("user", "birthday", "1996-02-15");
        jedis.hsetnx("user", "hobby", "编程、旅游、健身");
        jedis.hkeys("user").forEach(key -> {
//            遍历输出全部信息
            System.out.println(jedis.hget("user", key));
        });
//        System.out.println(jedis.hgetAll("user"));
    }

    @Test
    public void test06() throws InterruptedException {
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("接收消息："+ message + "，来自于频道：" + channel);
            }
        }, "LiYe", "JingJie");
        TimeUnit.MINUTES.sleep(1);
    }

//    使用redis发布(生产)消息
    @Test
    public void test07() {
        jedis.publish("LiYe", "h");
    }
}
