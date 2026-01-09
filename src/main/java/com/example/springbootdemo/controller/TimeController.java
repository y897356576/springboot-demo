package com.example.springbootdemo.controller;

import com.example.springbootdemo.feignClient.TimeApiClient;
import com.example.springbootdemo.model.Time;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "时间查询", description = "时间查询 API")
@RestController
@RequestMapping("/api/time")
public class TimeController {

    private final TimeApiClient timeApiClient;

    public TimeController(TimeApiClient timeApiClient) {
        this.timeApiClient = timeApiClient;
    }



    @Operation(summary = "时间查询", description = "获取北京时间")
    @GetMapping("/beijing")
    public ResponseEntity<Time> getBeijingTime() {
        Time time = timeApiClient.getBeijingTime();
        return ResponseEntity.ok(time);
    }

}
