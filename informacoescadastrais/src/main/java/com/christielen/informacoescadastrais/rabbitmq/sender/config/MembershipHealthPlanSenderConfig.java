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
public class MembershipHealthPlanSenderConfig {

    @Value("${queue.membershipHealthPlan}")
    private String queueMembershipHealthPlan;

    @Value("${exchange.membershipHealthPlan}")
    private String exchangeMembershipHealthPlan;

    @Value("${routingKey.membershipHealthPlan}")
    private String routingKeyMembershipHealthPlan;

    @Bean
    @Qualifier("queueMembershipHealthPlan")
    public Queue queueMembershipHealthPlan() {
        return new Queue(queueMembershipHealthPlan, true);
    }

    @Bean
    DirectExchange exchangeMembershipHealthPlan() {
        return new DirectExchange(exchangeMembershipHealthPlan);
    }

    @Bean
    Binding membershipHealthPlanBinding(Queue queueMembershipHealthPlan, DirectExchange exchangeMembershipHealthPlan) {
        return BindingBuilder.bind(queueMembershipHealthPlan)
                .to(exchangeMembershipHealthPlan)
                .with(routingKeyMembershipHealthPlan);
    }
}