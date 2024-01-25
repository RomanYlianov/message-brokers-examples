package com.example.ibmmqdemoboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SensorData implements Serializable {

    private Integer id;

    private String name;

    private Double value;

}
