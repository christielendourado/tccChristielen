package com.christielen.integrativo.rabbitmq.sender;

import com.christielen.integrativo.json.MembershipHealthPlanCodeJson;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipHealthPlanCodeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.membershipHealthPlanCode}")
    private String exchangeMembershipHealthPlanCode;

    @Value("${routingKey.membershipHealthPlanCode}")
    private String routingKeyMembershipHealthPlanCode;

    public void send(final MembershipHealthPlanCodeJson membershipHealthPlanJson) throws Exception {
        Gson gson = new Gson();

        rabbitTemplate.convertAndSend(
                exchangeMembershipHealthPlanCode,
                routingKeyMembershipHealthPlanCode,
                new Message(gson.toJson(membershipHealthPlanJson).getBytes(), new MessageProperties()));
    }
}