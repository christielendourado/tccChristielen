package com.christielen.integrativo.rabbitmq.consumer;

import com.christielen.integrativo.json.AppointmentSchedulerPlanJson;
import com.christielen.integrativo.json.MembershipHealthPlanCodeJson;
import com.christielen.integrativo.json.MembershipHealthPlanJson;
import com.christielen.integrativo.rabbitmq.sender.MembershipHealthPlanCodeSender;
import com.christielen.integrativo.util.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentSchedulerConsumer {

    @RabbitListener(queues = {"${queue.appointmentScheduler}"})
    public String process(final AppointmentSchedulerPlanJson appointmentSchedulerPlanJson) throws Exception {

        log.info("AppointmentSchedulerPlanJson: " + appointmentSchedulerPlanJson);

        //Envia as informações para o SGPS e ele retorna se foi autorizado ou não
        //De exemplo será sempre autorizado
        return "Autorizado";
    }
}
