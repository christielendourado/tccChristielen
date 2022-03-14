package com.christielen.informacoescadastrais.rabbitmq.sender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthPlanCardSenderConfig {

    @Value("${queue.healthPlanCard}")
    private String queueHealthPlanCard;

    @Value("${exchange.healthPlanCard}")
    private String exchangeHealthPlanCard;

    @Value("${routingKey.healthPlanCard}")
    private String routingKeyHealthPlanCard;

    @Bean
    @Qualifier("queueHealthPlanCard")
    public Queue queueHealthPlanCard() {
        return new Queue(queueHealthPlanCard, true);
    }

    @Bean
    DirectExchange exchangeHealthPlanCard() {
        return new DirectExchange(exchangeHealthPlanCard);
    }

    @Bean
    Binding healthPlanCardBinding(Queue queueHealthPlanCard, DirectExchange exchangeHealthPlanCard) {
        return BindingBuilder.bind(queueHealthPlanCard)
                .to(exchangeHealthPlanCard)
                .with(routingKeyHealthPlanCard);
    }
}