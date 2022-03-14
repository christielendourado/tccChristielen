package com.christielen.informacoescadastrais.rabbitmq.sender;

import com.christielen.informacoescadastrais.json.MembershipHealthPlanJson;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipHealthPlanSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.membershipHealthPlan}")
    private String exchangeMembershipHealthPlan;

    @Value("${routingKey.membershipHealthPlan}")
    private String routingKeyMembershipHealthPlan;

    public void send(MembershipHealthPlanJson membershipHealthPlanJson) throws Exception {
        Gson gson = new Gson();

        rabbitTemplate.convertAndSend(
                exchangeMembershipHealthPlan,
                routingKeyMembershipHealthPlan,
                new Message(gson.toJson(membershipHealthPlanJson).getBytes(), new MessageProperties()));
    }
}