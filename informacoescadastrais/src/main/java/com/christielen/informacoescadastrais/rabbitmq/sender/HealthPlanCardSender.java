package com.christielen.informacoescadastrais.rabbitmq.sender;

import com.christielen.informacoescadastrais.json.HealthPlanCardJson;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HealthPlanCardSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.healthPlanCard}")
    private String exchangeHealthPlanCard;

    @Value("${routingKey.healthPlanCard}")
    private String routingKeyHealthPlanCard;

    public void send(HealthPlanCardJson membershipHealthPlanJson) throws Exception {
        Gson gson = new Gson();

        rabbitTemplate.convertAndSend(
                exchangeHealthPlanCard,
                routingKeyHealthPlanCard,
                new Message(gson.toJson(membershipHealthPlanJson).getBytes(), new MessageProperties()));
    }
}