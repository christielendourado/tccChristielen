package com.christielen.informacoescadastrais.rest;

import com.christielen.informacoescadastrais.security.jwt.JwtTokenProvider;
import com.christielen.informacoescadastrais.service.HealthPlanCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("healthPlanCard")
public class HealthPlanCardController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private HealthPlanCardService healthPlanCardService;

    @PostMapping("/add")
    public void addMembershipHealthPlan(@RequestHeader("Authorization") String token) throws Exception {

        String username = jwtTokenProvider.buscarLogin(token);
        healthPlanCardService.add(username);
    }
}
