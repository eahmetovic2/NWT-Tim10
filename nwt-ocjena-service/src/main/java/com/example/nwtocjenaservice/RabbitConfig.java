package com.example.nwtocjenaservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitConfig implements RabbitListenerConfigurer {

    public static final String QUEUE_UCENIK = "ucenik-queue";
    public static final String QUEUE_DEAD_UCENIK = "dead-ucenik-queue";
    public static final String EXCHANGE_UCENIK = "ucenik-exchange";

    public static final String QUEUE_PREDMET = "predmet-queue";
    public static final String QUEUE_DEAD_PREDMET = "dead-predmet-queue";
    public static final String EXCHANGE_PREDMET = "predmet-exchange";

    public static final String QUEUE_NASTAVNIK = "nastavnik-queue";
    public static final String QUEUE_DEAD_NASTAVNIK = "dead-nastavnik-queue";
    public static final String EXCHANGE_NASTAVNIK = "nastavnik-exchange";

    @Bean
    Queue ucenikQueue() {

        return QueueBuilder.durable(QUEUE_UCENIK)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_UCENIK)
                .withArgument("x-message-ttl", 5000)
                .build();
    }

    @Bean
    Queue nastavnikQueue() {

        return QueueBuilder.durable(QUEUE_NASTAVNIK)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_NASTAVNIK)
                .withArgument("x-message-ttl", 5000)
                .build();
    }

    @Bean
    Queue predmetQueue() {

        return QueueBuilder.durable(QUEUE_PREDMET)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_PREDMET)
                .withArgument("x-message-ttl", 5000)
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_UCENIK).build();
    }

    @Bean
    Exchange ucenikExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_UCENIK).build();
    }

    @Bean
    Exchange predmetExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_PREDMET).build();
    }

    @Bean
    Exchange nastavnikExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NASTAVNIK).build();
    }

    @Bean
    Binding binding(Queue ucenikQueue, TopicExchange ucenikExchange) {
        return BindingBuilder.bind(ucenikQueue).to(ucenikExchange).with(QUEUE_UCENIK);
    }

    @Bean
    Binding binding1(Queue predmetQueue, TopicExchange predmetExchange) {
        return BindingBuilder.bind(predmetQueue).to(predmetExchange).with(QUEUE_PREDMET);
    }

    @Bean
    Binding binding2(Queue nastavnikQueue, TopicExchange nastavnikExchange) {
        return BindingBuilder.bind(nastavnikQueue).to(nastavnikExchange).with(QUEUE_NASTAVNIK);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
