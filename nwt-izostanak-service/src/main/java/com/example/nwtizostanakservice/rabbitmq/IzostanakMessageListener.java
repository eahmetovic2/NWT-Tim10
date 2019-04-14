package com.example.nwtizostanakservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.nwtizostanakservice.RabbitConfig;
import com.example.nwtizostanakservice.model.Izostanak;
import com.example.nwtizostanakservice.service.IzostanakService;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class IzostanakMessageListener {

    @Autowired
    private IzostanakService izostanakService;

    @RabbitListener(queues = RabbitConfig.QUEUE_IZOSTANAK)
    public void processOrder(Izostanak izostanak) {
        izostanakService.save(izostanak);
    }
}
