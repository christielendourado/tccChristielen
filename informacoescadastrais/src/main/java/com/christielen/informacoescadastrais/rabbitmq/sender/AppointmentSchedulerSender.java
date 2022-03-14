package com.christielen.informacoescadastrais.rabbitmq.sender;

import com.christielen.informacoescadastrais.json.AppointmentSchedulerPlanJson;
import com.christielen.informacoescadastrais.json.MembershipHealthPlanJson;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppointmentSchedulerSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.appointmentScheduler}")
    private String exchangeAppointmentScheduler;

    @Value("${routingKey.appointmentScheduler}")
    private String routingKeyAppointmentScheduler;

    public String send(AppointmentSchedulerPlanJson appointmentSchedulerPlanJson) throws Exception {
        Gson gson = new Gson();

        String response = rabbitTemplate.convertSendAndReceive(
                exchangeAppointmentScheduler,
                routingKeyAppointmentScheduler,
                new Message(gson.toJson(appointmentSchedulerPlanJson).getBytes(), new MessageProperties())).toString();
        return response;
    }
}