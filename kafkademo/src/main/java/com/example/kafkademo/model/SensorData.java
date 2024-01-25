package com.example.kafkademo.model;

import lombok.Data;

import java.time.Instant;


@Data
public class SensorData {

    private Long id;

    private String name;

    private Double value;

   // private Instant time;



}
