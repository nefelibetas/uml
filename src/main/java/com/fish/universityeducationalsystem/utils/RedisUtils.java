package com.fish.universityeducationalsystem.utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Value("${jwt.expireTime}")
    Long expireTime;
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
