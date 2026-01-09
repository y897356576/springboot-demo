package com.example.springbootdemo.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Time {

    private OffsetDateTime datetime;

    private String timezone;

}
