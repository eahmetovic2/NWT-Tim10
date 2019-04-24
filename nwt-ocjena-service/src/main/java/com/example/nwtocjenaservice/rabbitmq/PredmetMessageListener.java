package com.example.nwtocjenaservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.RabbitConfig;
import com.example.nwtocjenaservice.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PredmetMessageListener {

    @Autowired
    private PredmetService predmetService;

    @RabbitListener(queues = RabbitConfig.QUEUE_PREDMET)
    public void processOrder(Predmet predmet) {
        predmetService.save(predmet);
    }
}
