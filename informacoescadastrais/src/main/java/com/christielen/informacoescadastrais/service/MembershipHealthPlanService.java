package com.christielen.informacoescadastrais.service;


import com.christielen.informacoescadastrais.entity.*;
import com.christielen.informacoescadastrais.enuns.Status;
import com.christielen.informacoescadastrais.exception.AssociateIsMembershipHealthPlanException;
import com.christielen.informacoescadastrais.exception.FieldValidationException;
import com.christielen.informacoescadastrais.exception.NotAssociateException;
import com.christielen.informacoescadastrais.exception.NotHealthPlanException;
import com.christielen.informacoescadastrais.json.MembershipHealthPlanJson;
import com.christielen.informacoescadastrais.rabbitmq.sender.MembershipHealthPlanSender;
import com.christielen.informacoescadastrais.repository.*;
import com.christielen.informacoescadastrais.util.AgeCalculatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
public class MembershipHealthPlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private HealthPlanRepository healthPlanRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private MembershipHealthPlanRepository membershipHealthPlanRepository;

    @Autowired
    private MembershipHealthPlanSender membershipHealthPlanSender;

    public MembershipHealthPlan add(String username, MembershipHealthPlanJson membershipHealthPlanJson) throws Exception {
        log.info("Usuario: " + username);

        User user = userRepository.findByUsername(username);
        Associate associate = associateRepository.findByUser(user.getId());

        if(associate==null){
            throw new NotAssociateException("Usuario com CPF " + associate.getCpf() + " nao e do tipo associado");
        }

        if (membershipHealthPlanRepository.findByAssociate(associate.getId())!=null) {
            throw new AssociateIsMembershipHealthPlanException("Associado com CPF " + associate.getCpf() + " ja esta associado a um plano");
        }

        HealthPlan healthPlan = healthPlanRepository.findByCode(membershipHealthPlanJson.getHealthPlanCode());

        if(healthPlan==null){
            throw new NotHealthPlanException("Codigo de plano " + membershipHealthPlanJson.getHealthPlanCode() + " nao cadastrado");
        }

        log.info("Nome do Plano de saude: " + healthPlan.getName());
        log.info("Codigo do Plano de saude: " + healthPlan.getCode());

        MembershipHealthPlan membershipHealthPlan = new MembershipHealthPlan();
        membershipHealthPlan.setAssociate(associate);
        membershipHealthPlan.setHealthPlan(healthPlan);
        membershipHealthPlan.setDental(membershipHealthPlanJson.getDental());
        membershipHealthPlan.setStatus(statusRepository.findByDescription(Status.ATIVO.getDescription()));

        if (!associate.getBirthDate().matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new FieldValidationException("O campo do tipo data deve seguir o formato dd/MM/yyyy");
        }

        int year = Integer.valueOf(associate.getBirthDate().substring(6,10));
        int month = Integer.valueOf(associate.getBirthDate().substring(3,5));
        int day = Integer.valueOf(associate.getBirthDate().substring(0,2));
        
        LocalDate birthDate = LocalDate.of(year, month, day);
        Integer age = AgeCalculatorUtil.calculateAge(birthDate, LocalDate.now());

        BigDecimal value = BigDecimal.ZERO;
        for (AgeGroupValues ageGroupValues : healthPlan.getAgeGroupValues()){
            if(age >= ageGroupValues.getAgeGroup().getInitialAge() && age <= ageGroupValues.getAgeGroup().getFinalAge()){
                value = ageGroupValues.getValue();

                if(membershipHealthPlan.getDental() && !healthPlan.getPlanType().getDental()){
                    BigDecimal dentalValue = value.multiply(new BigDecimal(0.15));
                    value = value.add(dentalValue);
                }
                break;
            }
        }

        membershipHealthPlan.setValue(value);

        membershipHealthPlan = membershipHealthPlanRepository.save(membershipHealthPlan);

        generateCode(membershipHealthPlan.getId(), associate, value, membershipHealthPlanJson);

        return membershipHealthPlan;
    }

    public void generateCode(ObjectId objectId, Associate associate, BigDecimal value, MembershipHealthPlanJson membershipHealthPlanJson) throws Exception {
        membershipHealthPlanJson.setAssociado(associate.getCpf());
        membershipHealthPlanJson.setValue(value);
        membershipHealthPlanJson.setObjectId(objectId.toString());

        membershipHealthPlanSender.send(membershipHealthPlanJson);
    }

    public MembershipHealthPlan get(String username) throws NotAssociateException {
        User user = userRepository.findByUsername(username);
        Associate associate = associateRepository.findByUser(user.getId());

        if(associate==null){
            throw new NotAssociateException("Usuario com CPF " + associate.getCpf() + " nao e do tipo associadp");
        }

        return membershipHealthPlanRepository.findByAssociate(associate.getId());
    }
}