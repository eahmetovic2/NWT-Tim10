package com.example.nwtocjenaservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.RabbitConfig;
import com.example.nwtocjenaservice.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class NastavnikMessageListener {

    @Autowired
    private NastavnikService nastavnikService;

    @RabbitListener(queues = RabbitConfig.QUEUE_NASTAVNIK)
    public void processOrder(Nastavnik nastavnik) {
        nastavnikService.save(nastavnik);
    }
}
