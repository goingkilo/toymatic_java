package com.batterbox.batterbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order {

    Map<String,Integer> orders ;

    private String name, address, cellNumber;
    private PaymentOption paymentOption = PaymentOption.COD;

    public Order(String name) {
        this.name = name;
        orders = new HashMap<>();
    }

    //for jackson
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Integer> orders) {
        this.orders = orders;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    //^end: for jackson

    public void addProduct(String product, int count) {
        if( orders.containsKey(product)){
            int c = orders.get(product);
            c += count;
            orders.put( product, c);
        }
        else {
            orders.put( product, count);
        }
    }

    @Override
    public String toString() {
            final StringBuilder sb = new StringBuilder(name);
        sb.append(" ").append(cellNumber);
        sb.append("\n  ").append(address);
        sb.append("\n  -- ");
        for( Map.Entry e : orders.entrySet()) {
            sb.append( "\n\t").append(e.getKey()).append(":").append(e.getValue());
        }
        sb.append("\n  ").append(paymentOption);
        sb.append("\n");
        return sb.toString();
    }

    public static Order fromJSONString(String orderString) {

        JSONObject o = new JSONObject(orderString);

        Order order = new Order(o.getString("name"));

        order.setAddress( o.getString("address"));
        order.setCellNumber(o.getString("cellNumber"));
        order.setPaymentOption(PaymentOption.valueOf(o.getString("paymentOption")));

        JSONObject map = o.getJSONObject("orders");
        Iterator<String> keys = map.keys();
        while( keys.hasNext()){
            String key  = keys.next();
            int i = map.getInt(key);
            order.addProduct( key,i);

        }
        return order;

    }


    public String toJSONString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(this);
        return json;
    }
}
