package com.example.nwtizostanakservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nwtizostanakservice.RabbitConfig;
import com.example.nwtizostanakservice.model.Izostanak;


@Service
public class IzostanakMessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public IzostanakMessageSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendIzostanak(Izostanak izostanak) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_IZOSTANAK, izostanak);
    }
}
