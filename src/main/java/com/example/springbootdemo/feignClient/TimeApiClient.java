package com.example.springbootdemo.feignClient;

import com.example.springbootdemo.model.Time;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "time-api", url = "http://worldtimeapi.org")
public interface TimeApiClient {

    @GetMapping("/api/timezone/Asia/Shanghai")
    Time getBeijingTime();

}
