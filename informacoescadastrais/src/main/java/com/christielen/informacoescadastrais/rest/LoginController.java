package com.christielen.informacoescadastrais.rest;

import com.christielen.informacoescadastrais.entity.User;
import com.christielen.informacoescadastrais.repository.UserRepository;
import com.christielen.informacoescadastrais.security.jwt.AccountCredentials;
import com.christielen.informacoescadastrais.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/login", produces = "application/json")
    public @ResponseBody String login(@RequestBody AccountCredentials accountCredentials) {
       User user =  userRepository.findByUsernameAndPassword(accountCredentials.getUsername(), accountCredentials.getPassword());

       if(user!=null){
           try {
               return jwtTokenProvider.createToken(accountCredentials.getUsername());
           } catch (IOException e) {
               log.error("Erro ao gerar o token");
           }
       }else{
           log.error("Usuario nao encontrado ou senha inválida");
       }

       return "";
    }
}
