package com.mono.monochrome;

import com.mono.monochrome.bean.RateLimiter;
import com.mono.monochrome.bean.RedisCounter;
import com.mono.monochrome.di.ApplicationContext;
import com.mono.monochrome.di.impl.ClassPathXmlApplicationContext;

/**
 * @author monochrome
 * @date 2022/7/19
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RedisCounter redisCounter = (RedisCounter) applicationContext.getBean("redisCounter");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        System.out.println(redisCounter);
        rateLimiter.test();
        System.out.println(redisCounter == rateLimiter.getRedisCounter());
    }
}
