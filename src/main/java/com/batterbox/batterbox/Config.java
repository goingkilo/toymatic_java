package com.batterbox.batterbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class Config {

    @Bean
    Jedis jedis() throws URISyntaxException {
        String s = System.getenv("REDIS_URL");
        URI redisURI = new URI(System.getenv("REDIS_URL"));
        return new Jedis(redisURI);
    }

    @Bean
    BBRepository rep(){
        return new BBRepository();
    }


}
