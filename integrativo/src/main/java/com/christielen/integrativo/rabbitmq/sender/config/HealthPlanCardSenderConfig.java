package com.christielen.integrativo.rabbitmq.sender.config;

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

    @Value("${queue.healthPlanCardCode}")
    private String queueHealthPlanCardCode;

    @Value("${exchange.healthPlanCardCode}")
    private String exchangeHealthPlanCardCode;

    @Value("${routingKey.healthPlanCardCode}")
    private String routingKeyHealthPlanCardCode;

    @Bean
    @Qualifier("queueHealthPlanCardCode")
    public Queue queueHealthPlanCardCode() {
        return new Queue(queueHealthPlanCardCode, true);
    }

    @Bean
    DirectExchange exchangeHealthPlanCardCode() {
        return new DirectExchange(exchangeHealthPlanCardCode);
    }

    @Bean
    Binding healthPlanCardBinding(Queue queueHealthPlanCardCode, DirectExchange exchangeHealthPlanCardCode) {
        return BindingBuilder.bind(queueHealthPlanCardCode)
                .to(exchangeHealthPlanCardCode)
                .with(routingKeyHealthPlanCardCode);
    }
}