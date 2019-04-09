package com.example.nwtocjenaservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.RabbitConfig;
import com.example.nwtocjenaservice.service.UcenikService;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UcenikMessageListener {

    @Autowired
    private UcenikService ucenikService;

    @RabbitListener(queues = RabbitConfig.QUEUE_UCENIK)
    public void processOrder(Ucenik ucenik) {
        ucenikService.save(ucenik);
    }
}
