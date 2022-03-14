package com.christielen.informacoescadastrais.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Kong {

    @Value("${kong.link}")
    public String kongLink;

    public Map<String, String> criarConsumerJWT(String usuario) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = kongLink + "/consumers/" + usuario + "/jwt";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> hashMap = new HashMap<String, String>();
        JsonNode root = mapper.readTree(response.getBody());

        JsonNode secret = root.findValues("secret").get(0);
        JsonNode key = root.findValues("key").get(0);

        hashMap.put("key", key.asText());
        hashMap.put("secret", secret.asText());

        return hashMap;
    }
}
