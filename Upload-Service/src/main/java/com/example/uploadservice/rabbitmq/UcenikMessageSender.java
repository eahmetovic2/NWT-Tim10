package com.example.uploadservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.RabbitConfig;


@Service
public class UcenikMessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public UcenikMessageSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendUcenik(Ucenik ucenik) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_UCENIK, ucenik);
    }
}
