package com.batterbox.batterbox;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by kraghunathan on 3/23/19.
 */
public class OrderTest extends TestCase {

    Order order;
    String[] products = {"RICE","RAGI","MILLET","VADAI","APPAM"};

    public void setUp() throws Exception {
        super.setUp();

        order = new Order("pavikra");
        order.addProduct(products[0],1);
        order.addProduct(products[2],3);

        order.setCellNumber("123432");
        order.setAddress("B304,Skylark Esta");

        order.setPaymentOption(PaymentOption.COD);
    }

    public void testToString() throws Exception {
        System.out.println(order.toString());

    }

    public void testToJSON() throws Exception {

        String json = order.toJSONString();

        Order newOrder = Order.fromJSONString(json);

        Assert.assertEquals(order.toString(), newOrder.toString());

    }
}