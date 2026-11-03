package com.ktech.rest;

import com.ktech.feign.GreetingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class WelcomeRestController {


    private final GreetingFeignClient greetingFeignClient;

    @GetMapping("/welcome")
    public String welcomeRest(){

        String response = greetingFeignClient.greetRestAPI();
//
//        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8083/greet", String.class);

        return response + " ,  CT -Program";
    }
}
