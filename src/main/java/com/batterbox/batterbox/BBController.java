package com.batterbox.batterbox;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class BBController {

    @RequestMapping("/core")
    public String index(@RequestParam(value="name", defaultValue="World") String name) {

        return new StringBuilder( "Hello" )
                .append(" ")
                .append(name)
                .toString();

    }
    @RequestMapping("/getNextDeliveryDate")
    public String nextDeliveryDate() {

        return new Date()
                .toString();

    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String takeBatterboxOrder(
            @RequestParam(value="name") String  name,
            @RequestParam(value="address") String  address,
            @RequestParam(value="phone") String  phone,
            @RequestParam(value="idli") int  idli,
            @RequestParam(value="vada") int vada,
            @RequestParam(value="ragi") int ragi,
            @RequestParam(value="millet") int millet,
            @RequestParam(value="appam") int appam,
            @RequestParam(value="payment") String  payment
            ) {


        Order order  = new Order(name);
        order.setAddress(address);
        order.setCellNumber(phone);
        order.setPaymentOption(PaymentOption.valueOf(payment));
        if( idli != 0) {
            order.addProduct(Constants.IDLI,idli);
        }
        if( vada != 0) {
            order.addProduct(Constants.VADA,vada);
        }
        if( ragi != 0) {
            order.addProduct(Constants.RAGI,ragi);
        }
        if( millet != 0) {
            order.addProduct(Constants.MILLET,millet);
        }
        if( appam != 0) {
            order.addProduct(Constants.APPAM,appam);
        }

        System.out.println( order.toString());


        return new StringBuilder( "Hello" )
                .append(" ")
                .append(order)
                .toString();

    }

    @RequestMapping(value = "/batterbox")
    public String showBBForm(
            @RequestParam(value="order") Order order) {

        return "forward:/static/batterbox.html";

    }


}