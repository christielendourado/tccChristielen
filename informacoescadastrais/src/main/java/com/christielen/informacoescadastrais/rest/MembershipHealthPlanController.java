package com.christielen.informacoescadastrais.rest;


import com.christielen.informacoescadastrais.entity.MembershipHealthPlan;
import com.christielen.informacoescadastrais.json.MembershipHealthPlanJson;
import com.christielen.informacoescadastrais.service.MembershipHealthPlanService;
import com.christielen.informacoescadastrais.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("membershipHealthPlan")
public class MembershipHealthPlanController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MembershipHealthPlanService membershipHealthPlanService;

    @PostMapping("/add")
    public MembershipHealthPlan addMembershipHealthPlan(@RequestBody MembershipHealthPlanJson membershipHealthPlanJson,
                                                        @RequestHeader("Authorization") String token) throws Exception {

        String username = jwtTokenProvider.buscarLogin(token);
        return membershipHealthPlanService.add(username, membershipHealthPlanJson);
    }

    @RequestMapping(method = RequestMethod.GET)
    public MembershipHealthPlan getMembershipHealthPlan(@RequestHeader("Authorization") String token) throws Exception {

        String username = jwtTokenProvider.buscarLogin(token);
        return membershipHealthPlanService.get(username);
    }
}