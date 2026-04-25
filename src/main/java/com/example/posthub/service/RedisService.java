package com.example.posthub.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    public void setWithTTL(String key, Object value, long minutes) {
        redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void incrementVirality(Long postId, int value) {
        String key = "post:" + postId + ":virality_score";
        redisTemplate.opsForValue().increment(key, value);
    }

    public Object getScore(Long postId) {
        return redisTemplate.opsForValue().get("post:" + postId + ":virality_score");
    }
}
