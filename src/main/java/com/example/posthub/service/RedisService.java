package com.example.posthub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void incrementVirality(Long postId, int value) {
        String key = "post:" + postId + ":virality_score";
        redisTemplate.opsForValue().increment(key, value);
    }

    public String getScore(Long postId) {
        return redisTemplate.opsForValue().get("post:" + postId + ":virality_score");
    }
}
