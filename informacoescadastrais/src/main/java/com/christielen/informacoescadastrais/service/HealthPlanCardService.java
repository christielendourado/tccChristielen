package com.christielen.informacoescadastrais.service;


import com.christielen.informacoescadastrais.entity.*;
import com.christielen.informacoescadastrais.enuns.Status;
import com.christielen.informacoescadastrais.exception.AssociateIsMembershipHealthPlanException;
import com.christielen.informacoescadastrais.exception.NotActiveMembershipHealthPlanException;
import com.christielen.informacoescadastrais.exception.NotAssociateException;
import com.christielen.informacoescadastrais.json.HealthPlanCardJson;
import com.christielen.informacoescadastrais.rabbitmq.sender.HealthPlanCardSender;
import com.christielen.informacoescadastrais.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HealthPlanCardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private MembershipHealthPlanRepository membershipHealthPlanRepository;

    @Autowired
    private HealthPlanCardSender healthPlanCardSender;

    public void add(String username) throws Exception {
        log.info("Usuario: " + username);
        User user = userRepository.findByUsername(username);
        Associate associate = associateRepository.findByUser(user.getId());

        if(associate==null){
            throw new NotAssociateException("Usuario com CPF " + associate.getCpf() + " nao e do tipo associado");
        }

        MembershipHealthPlan membershipHealthPlan = membershipHealthPlanRepository.findByAssociate(associate.getId());
        if (membershipHealthPlan!=null && (membershipHealthPlan.getHealthPlanCard()!=null && !membershipHealthPlan.getHealthPlanCard().isEmpty() )) {
            throw new AssociateIsMembershipHealthPlanException("Associado com CPF " + associate.getCpf() + " ja esta associado a um plano e possui um numero de carteirinha");
        }

        log.info("Status do Plano de saude: " + membershipHealthPlan.getStatus().getDescription());
        if(!membershipHealthPlan.getStatus().getDescription().equals(Status.ATIVO.getDescription())){
            throw new NotActiveMembershipHealthPlanException("Usuario com CPF " + associate.getCpf() + " esta associado a um plano que nao esta ATIVO");
        }

        generateCode(membershipHealthPlan);
    }

    public void generateCode(MembershipHealthPlan membershipHealthPlan) throws Exception {
        HealthPlanCardJson healthPlanCardJson = new HealthPlanCardJson();
        healthPlanCardJson.setMembershipHealthPlanCode(membershipHealthPlan.getCode());
        healthPlanCardJson.setObjectId(membershipHealthPlan.getId().toString());

        healthPlanCardSender.send(healthPlanCardJson);
    }
}