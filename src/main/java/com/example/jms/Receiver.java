package com.example.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "topic1", containerFactory = "myFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

}
