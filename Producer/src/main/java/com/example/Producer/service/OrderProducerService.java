package com.example.Producer.service;


import com.example.Producer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private static final String ORDER_KEY = "orders";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void produceOrder(Order order) {
        // Push order to Redis List
        redisTemplate.opsForList().rightPush(ORDER_KEY, order);
        System.out.println("Produced Order: " + order);
    }
}
