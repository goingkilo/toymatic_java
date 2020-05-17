package com.batterbox.batterbox;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BBRepository {

    @Autowired
    Jedis jedis;

    public void save(Order order) throws JsonProcessingException {
        jedis.lpush("orders", order.toJSONString());
    }

    public List<String> getAllOrders(){
        Long numOrders = jedis.llen("orders");

        return IntStream.range( 0, numOrders.intValue())
                .mapToObj(i -> jedis.lindex("orders", i))
                .collect(Collectors.toList());
    }


    public String getNextDeliveryDate() {

        return jedis.get("next_delivery_date");
    }

    public String setNextDeliveryDate(String date) {
        String currentDate = jedis.get("next_delivery_date");
        if( currentDate != null) {
            jedis.lpush("previous_delivery_dates", currentDate);
        }
        return jedis.set("next_delivery_date", date);
    }
}
