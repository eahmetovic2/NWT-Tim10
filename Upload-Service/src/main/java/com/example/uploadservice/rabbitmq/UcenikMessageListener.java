package com.example.uploadservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.RabbitConfig;

@Component
public class UcenikMessageListener {

    // @RabbitListener(queues = RabbitConfig.QUEUE_UCENIK)
    // public void processOrder(Ucenik ucenik) {
    //     System.out.println("Order Received: "+ucenik);
    // }
}
