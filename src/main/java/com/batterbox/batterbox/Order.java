package com.batterbox.batterbox;

import java.util.HashMap;
import java.util.Map;

public class Order {

    Map<String,Integer> orders ;

    private String name, address, cellNumber;
    private PaymentOption paymentOption = PaymentOption.COD;

    public Order(String cellNumber) {
        this.cellNumber = cellNumber;
        orders = new HashMap<>();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public void addProduct(String product, int count) {
        if( orders.containsKey(product)){
            int c = orders.get(product);
            c += count;
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
}
