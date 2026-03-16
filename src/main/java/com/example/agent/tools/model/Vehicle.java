package com.example.agent.tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Vehicle {
    private String assetId;
    private String make;
    private String model;
    private String year;
    private String colour;
    private Person owner;
    private Person driver;
    private List<Person> passengers;
}
