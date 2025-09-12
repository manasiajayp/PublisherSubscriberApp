package com.example.Producer.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//
//
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//
//    private final StringRedisTemplate redis;
//    private final ObjectMapper mapper = new ObjectMapper();
//    private static final String QUEUE_KEY = "order-queue";
//
//    @Autowired
//    public OrderController(StringRedisTemplate redis) {
//        this.redis = redis;
//    }
//
//    @PostMapping
//    public ResponseEntity<Map<String,String>> createOrder(@RequestBody(required = false) Map<String,Object> body) throws Exception {
//        String orderId = UUID.randomUUID().toString();
//        Map<String,Object> order = new HashMap<>();
//        order.put("orderId", orderId);
//        order.put("payload", body == null ? Map.of() : body);
//
//        String json = mapper.writeValueAsString(order);
//        redis.opsForList().rightPush(QUEUE_KEY, json);
//        return ResponseEntity.ok(Map.of("orderId", orderId));
//    }
//
//    // Health check for Redis connectivity
//    @GetMapping("/redis-test")
//    public String redisTest() {
//        try {
//            redis.opsForValue().set("ping", "pong");
//            String v = redis.opsForValue().get("ping");
//            return "Redis OK: " + v;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Redis ERROR: " + e.getMessage();
//        }
//    }
//}



import com.example.Producer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderProducerController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ORDER_KEY = "orders";

    @PostMapping("/produce")
    public String produceOrder(@RequestParam String item, @RequestParam int quantity) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, item, quantity);
        redisTemplate.opsForList().leftPush(ORDER_KEY, order);
        return "Order produced: " + order.toString();
    }
}
