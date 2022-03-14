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
public class MembershipHealthPlanSenderConfig {

    @Value("${queue.membershipHealthPlanCode}")
    private String queueMembershipHealthPlanCode;

    @Value("${exchange.membershipHealthPlanCode}")
    private String exchangeMembershipHealthPlanCode;

    @Value("${routingKey.membershipHealthPlanCode}")
    private String routingKeyMembershipHealthPlanCode;

    @Bean
    @Qualifier("queueMembershipHealthPlanCode")
    public Queue queueMembershipHealthPlanCode() {
        return new Queue(queueMembershipHealthPlanCode, true);
    }

    @Bean
    DirectExchange exchangeMembershipHealthPlanCode() {
        return new DirectExchange(exchangeMembershipHealthPlanCode);
    }

    @Bean
    Binding membershipHealthPlanBinding(Queue queueMembershipHealthPlanCode, DirectExchange exchangeMembershipHealthPlanCode) {
        return BindingBuilder.bind(queueMembershipHealthPlanCode)
                .to(exchangeMembershipHealthPlanCode)
                .with(routingKeyMembershipHealthPlanCode);
    }
}