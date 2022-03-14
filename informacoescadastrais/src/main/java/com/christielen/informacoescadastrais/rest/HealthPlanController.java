package com.christielen.informacoescadastrais.rest;

import com.christielen.informacoescadastrais.entity.HealthPlan;
import com.christielen.informacoescadastrais.repository.HealthPlanRepository;
import com.christielen.informacoescadastrais.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthPlanController {

    @Autowired
    private HealthPlanRepository healthPlanRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/healthPlan", produces = "application/json")
    public @ResponseBody List<HealthPlan> findAll(@RequestHeader("Authorization") String token){
        String username = jwtTokenProvider.buscarLogin(token);
        return healthPlanRepository.findAll();
    }
}
