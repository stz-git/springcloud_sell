package com.imooc.order.message;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("aQueue"),
            exchange = @Exchange("myExchange"),
            key = "a"
    ))
    public void process(String message){
        System.out.println("a_message: " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("bQueue"),
            exchange = @Exchange("myExchange"),
            key = "b"
    ))
    public void b_process(String message){
        System.out.println("b_message: " + message);
    }
}
