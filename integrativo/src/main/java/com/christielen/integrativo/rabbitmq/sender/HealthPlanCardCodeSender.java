package com.christielen.integrativo.rabbitmq.sender;

import com.christielen.integrativo.json.HealthPlanCardCodeJson;
import com.christielen.integrativo.json.MembershipHealthPlanCodeJson;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HealthPlanCardCodeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.healthPlanCardCode}")
    private String exchangeMembershipHealthPlanCode;

    @Value("${routingKey.healthPlanCardCode}")
    private String routingKeyMembershipHealthPlanCode;

    public void send(final HealthPlanCardCodeJson healthPlanCardCodeJson) throws Exception {
        Gson gson = new Gson();

        rabbitTemplate.convertAndSend(
                exchangeMembershipHealthPlanCode,
                routingKeyMembershipHealthPlanCode,
                new Message(gson.toJson(healthPlanCardCodeJson).getBytes(), new MessageProperties()));
    }
}