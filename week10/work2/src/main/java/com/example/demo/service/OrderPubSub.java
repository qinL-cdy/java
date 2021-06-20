package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

@Component
public class OrderPubSub extends JedisPubSub implements CommandLineRunner {

    @Autowired
    JedisPool jedisPool;

    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(this, "order");
    }

    public void publish(String id) {
        Jedis jedis = jedisPool.getResource();
        jedis.publish("order", id);
    }

    @Override
    public void onMessage(String channel, String message) {
        orderService.order(message);
    }
}
