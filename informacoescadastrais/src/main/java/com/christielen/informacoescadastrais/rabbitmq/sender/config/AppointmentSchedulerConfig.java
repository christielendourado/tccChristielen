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
public class AppointmentSchedulerConfig {

    @Value("${queue.appointmentScheduler}")
    private String queueAppointmentScheduler;

    @Value("${exchange.appointmentScheduler}")
    private String exchangeAppointmentScheduler;

    @Value("${routingKey.appointmentScheduler}")
    private String routingKeyAppointmentScheduler;

    @Bean
    @Qualifier("queueAppointmentScheduler")
    public Queue queueAppointmentScheduler() {
        return new Queue(queueAppointmentScheduler, true);
    }

    @Bean
    DirectExchange exchangeAppointmentScheduler() {
        return new DirectExchange(exchangeAppointmentScheduler);
    }

    @Bean
    Binding appointmentSchedulerBinding(Queue queueAppointmentScheduler, DirectExchange exchangeAppointmentScheduler) {
        return BindingBuilder.bind(queueAppointmentScheduler)
                .to(exchangeAppointmentScheduler)
                .with(routingKeyAppointmentScheduler);
    }
}