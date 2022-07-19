package com.mono.monochrome.bean;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class RateLimiter {
    private final RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public RedisCounter getRedisCounter() {
        return redisCounter;
    }

    public void test() {
        System.out.println("Hello");
        System.out.println(redisCounter);
    }
}
