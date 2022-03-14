package com.christielen.informacoescadastrais.service;

import com.christielen.informacoescadastrais.entity.*;
import com.christielen.informacoescadastrais.enuns.Status;
import com.christielen.informacoescadastrais.exception.*;
import com.christielen.informacoescadastrais.json.AppointmentSchedulerPlanJson;
import com.christielen.informacoescadastrais.rabbitmq.sender.AppointmentSchedulerSender;
import com.christielen.informacoescadastrais.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppointmentSchedulerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ConvenedRepository convenedRepository;

    @Autowired
    private MembershipHealthPlanRepository membershipHealthPlanRepository;

    @Autowired
    private AppointmentSchedulerSender appointmentSchedulerSender;

    public String authorization(String username, AppointmentSchedulerPlanJson appointmentSchedulerPlanJson) throws Exception {
        log.info("Usuario: " + username);

        User user = userRepository.findByUsername(username);
        Convened convened = convenedRepository.findByUser(user.getId());

        if(convened==null){
            throw new NotConvenedException("Usuario " + username + " nao e do tipo conveniado");
        }

        log.info("Usuario CPF: " + appointmentSchedulerPlanJson.getAssociateCpf());
        Associate associate = associateRepository.findByCpf(appointmentSchedulerPlanJson.getAssociateCpf());
        if(associate==null){
            throw new NotAssociateException("Usuario com CPF " + appointmentSchedulerPlanJson.getAssociateCpf() + " nao e do tipo associado");
        }

        MembershipHealthPlan membershipHealthPlan = membershipHealthPlanRepository.findByAssociate(associate.getId());
        if (membershipHealthPlan==null) {
            throw new AssociateIsNotMembershipHealthPlanException("Associado com CPF " + associate.getCpf() + " nao esta associado a um plano");
        }


        log.info("Status do Plano de saude: " + membershipHealthPlan.getStatus().getDescription());
        if (!membershipHealthPlan.getStatus().getDescription().equals(Status.ATIVO.getDescription())){
            throw new AssociateIsNotMembershipActiveHealthPlanException("Associado com CPF " + associate.getCpf() + " esta associado a um plano inativo ou suspenso");
        }

        log.info("Carteirinha do Plano de saude: " + membershipHealthPlan.getHealthPlanCard());
        appointmentSchedulerPlanJson.setHealthPlanCard(membershipHealthPlan.getHealthPlanCard());
        return appointmentSchedulerSender.send(appointmentSchedulerPlanJson);
    }
}