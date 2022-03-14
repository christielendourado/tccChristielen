package com.christielen.integrativo.rabbitmq.consumer;

import com.christielen.integrativo.json.HealthPlanCardCodeJson;
import com.christielen.integrativo.json.HealthPlanCardJson;
import com.christielen.integrativo.json.MembershipHealthPlanCodeJson;
import com.christielen.integrativo.json.MembershipHealthPlanJson;
import com.christielen.integrativo.rabbitmq.sender.HealthPlanCardCodeSender;
import com.christielen.integrativo.rabbitmq.sender.MembershipHealthPlanCodeSender;
import com.christielen.integrativo.util.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HealthPlanCardConsumer {

    @Autowired
    private HealthPlanCardCodeSender healthPlanCardCodeSender;

    @RabbitListener(queues = {"${queue.healthPlanCard}"})
    public void process(final HealthPlanCardJson healthPlanCardJson) throws Exception {

        log.info("HealthPlanCardJson: " + healthPlanCardJson);

        //Envia as informações para o SGPS e ele retorna o código da carteirinha
        //De exemplo será gerado um numero aleatório
        String code =  RandomString.getNumericString(17);
        code = code.substring(0, 5) + " " + code.substring(5, 9) + " " + code.substring(9, 13) + " " + code.substring(13, 17);

        HealthPlanCardCodeJson healthPlanCardCodeJson = new HealthPlanCardCodeJson();
        healthPlanCardCodeJson.setHealthCard(code);
        healthPlanCardCodeJson.setObjectId(healthPlanCardJson.getObjectId());

        healthPlanCardCodeSender.send(healthPlanCardCodeJson);
    }
}
