package com.batterbox.batterbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BBController {

    @Autowired
    BBRepository repository;

    @RequestMapping("/index")
    public ModelAndView index() {
        String date = nextDeliveryDate();
        if( date == null){
            ModelAndView mv = new ModelAndView("tbatterbox_date");
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("tbatterbox");
            mv.addObject("delivery_date", date);
            return mv;
        }
    }

    @RequestMapping("/getNextDeliveryDate")
    public String nextDeliveryDate() {
        String date = repository.getNextDeliveryDate();
        return date;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView getOrders(){

        List<String> orders = repository.getAllOrders();
        List<Order> o = orders
                .stream()
                .map(Order::fromJSONString)
                .collect(Collectors.toList());

        ModelAndView mv = new ModelAndView("tbatterbox_orders");
        mv.addObject("orders",o);
        return mv;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView takeBatterboxOrder(
            @RequestParam(value="name") String  name,
            @RequestParam(value="address") String  address,
            @RequestParam(value="phone") String  phone,
            @RequestParam(value="idli") int  idli,
            @RequestParam(value="vada") int vada,
            @RequestParam(value="ragi") int ragi,
            @RequestParam(value="millet") int millet,
            @RequestParam(value="appam") int appam,
            @RequestParam(value="payment") String  payment
            ) throws Exception {


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

        try {
            repository.save(order);
        }
        catch (JsonProcessingException e) {
            throw new Exception("this did not happen");
        }

        return getOrders();

    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(){

        Date d = new Date();

        long day = 24 * 60 * 60 * 1000;
        Date d1 = new Date(System.currentTimeMillis()- day);
        Date d2 = new Date(System.currentTimeMillis()- 2*day);
        Date d3 = new Date(System.currentTimeMillis()- 3*day);
        Date d4 = new Date(System.currentTimeMillis()- 4*day);

        List<String> dates = new ArrayList<>();
        dates.add( d1.toString());
        dates.add( d2.toString());
        dates.add( d3.toString());
        dates.add( d4.toString());

        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("curdate", d.toString());
        mv.addObject("dates", dates);
        return mv;
    }

    @RequestMapping(value = "/delivery_date", method = RequestMethod.POST)
    public ModelAndView setNextDeliveryDate(@RequestParam("delivery_date") String date){
        new Date(date);
        repository.setNextDeliveryDate(date);
        return getOrders();
    }


}
