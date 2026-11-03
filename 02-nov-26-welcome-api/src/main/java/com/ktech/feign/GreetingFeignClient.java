package com.ktech.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "02-NOV-26-GREET-API")
public interface GreetingFeignClient {

    @GetMapping("/greet")
    public String greetRestAPI();
}
