package com.christielen.informacoescadastrais.rabbitmq.consumer;

import com.christielen.informacoescadastrais.entity.MembershipHealthPlan;
import com.christielen.informacoescadastrais.json.MembershipHealthPlanCodeJson;
import com.christielen.informacoescadastrais.repository.MembershipHealthPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MembershipHealthPlanCodeConsumer {

    @Autowired
    private MembershipHealthPlanRepository membershipHealthPlanRepository;

    @RabbitListener(queues = {"${queue.membershipHealthPlanCode}"})
    public void process(final MembershipHealthPlanCodeJson membershipHealthPlanCodeJson) throws Exception {

        log.info("MembershipHealthPlanCodeJson: " + membershipHealthPlanCodeJson);

        MembershipHealthPlan membershipHealthPlan = membershipHealthPlanRepository.findById(new ObjectId(membershipHealthPlanCodeJson.getObjectId())).get();
        membershipHealthPlan.setCode(membershipHealthPlanCodeJson.getCode());
        membershipHealthPlanRepository.save(membershipHealthPlan);
    }
}