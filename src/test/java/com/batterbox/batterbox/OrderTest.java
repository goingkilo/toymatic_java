package com.batterbox.batterbox;

import junit.framework.TestCase;

/**
 * Created by kraghunathan on 3/23/19.
 */
public class OrderTest extends TestCase {

    Order order;
    String[] products = {"RICE","RAGI","MILLET","VADAI","APPAM"};

    public void setUp() throws Exception {
        super.setUp();

        order = new Order("12345667");
        order.addProduct(products[0],1);
        order.addProduct(products[2],3);

        order.setName("Pavikra");
        order.setAddress("b304,skylark esta");

        order.setPaymentOption(PaymentOption.COD);
    }

    public void testToString() throws Exception {
        System.out.println(order.toString());

    }
}