package com.christielen.integrativo.rabbitmq.consumer;

import com.christielen.integrativo.json.MembershipHealthPlanCodeJson;
import com.christielen.integrativo.json.MembershipHealthPlanJson;
import com.christielen.integrativo.rabbitmq.sender.MembershipHealthPlanCodeSender;
import com.christielen.integrativo.util.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MembershipHealthPlanConsumer {

    @Autowired
    private MembershipHealthPlanCodeSender membershipHealthPlanCodeSender;

    @RabbitListener(queues = {"${queue.membershipHealthPlan}"})
    public void process(final MembershipHealthPlanJson membershipHealthPlanJson) throws Exception {

        log.info("MembershipHealthPlanJson: " + membershipHealthPlanJson);

        //Envia as informações para o SGPS e ele retorna o código do plano
        //De exemplo será gerado um numero aleatório
        String code =  RandomString.getAlphaNumericString(20);
        MembershipHealthPlanCodeJson membershipHealthPlanCodeJson = new MembershipHealthPlanCodeJson();
        membershipHealthPlanCodeJson.setCode(code);
        membershipHealthPlanCodeJson.setObjectId(membershipHealthPlanJson.getObjectId());

        membershipHealthPlanCodeSender.send(membershipHealthPlanCodeJson);
    }
}
