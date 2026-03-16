package com.example.agent.tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Property {
    private String propertyType;
    private Location location;
}
