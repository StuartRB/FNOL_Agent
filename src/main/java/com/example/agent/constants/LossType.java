package com.example.agent.constants;

public enum LossType {
    LB01("Auto"),
    LB04("Property");

    private final String description;

    LossType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
