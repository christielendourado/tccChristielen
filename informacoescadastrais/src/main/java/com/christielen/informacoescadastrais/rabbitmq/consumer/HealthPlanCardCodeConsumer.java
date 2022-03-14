package com.christielen.informacoescadastrais.rabbitmq.consumer;

import com.christielen.informacoescadastrais.entity.MembershipHealthPlan;
import com.christielen.informacoescadastrais.json.HealthPlanCardCodeJson;
import com.christielen.informacoescadastrais.json.MembershipHealthPlanCodeJson;
import com.christielen.informacoescadastrais.repository.MembershipHealthPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HealthPlanCardCodeConsumer {

    @Autowired
    private MembershipHealthPlanRepository membershipHealthPlanRepository;

    @RabbitListener(queues = {"${queue.healthPlanCardCode}"})
    public void process(final HealthPlanCardCodeJson healthPlanCardCodeJson) throws Exception {

        log.info("HealthPlanCardCodeJson: " + healthPlanCardCodeJson);

        MembershipHealthPlan membershipHealthPlan = membershipHealthPlanRepository.findById(new ObjectId(healthPlanCardCodeJson.getObjectId())).get();
        membershipHealthPlan.setHealthPlanCard(healthPlanCardCodeJson.getHealthCard());
        membershipHealthPlanRepository.save(membershipHealthPlan);
    }
}