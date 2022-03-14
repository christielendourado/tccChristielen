package com.christielen.informacoescadastrais.rest;

import com.christielen.informacoescadastrais.json.AppointmentSchedulerPlanJson;
import com.christielen.informacoescadastrais.security.jwt.JwtTokenProvider;
import com.christielen.informacoescadastrais.service.AppointmentSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointmentScheduler")
public class AppointmentSchedulerController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AppointmentSchedulerService appointmentSchedulerService;

    @PostMapping
    public String authorization(@RequestBody AppointmentSchedulerPlanJson appointmentSchedulerPlanJson,
                                @RequestHeader("Authorization") String token) throws Exception {

        String username = jwtTokenProvider.buscarLogin(token);
        String authorization = appointmentSchedulerService.authorization(username, appointmentSchedulerPlanJson);
        return authorization;
    }
}