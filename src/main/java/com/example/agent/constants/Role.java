package com.example.agent.constants;

public enum Role {
    DRVR("Driver"),
    CMT("Claimant"),
    INSRD("Insured"),
    WIT("Witness"),
    PASS("Passenger"),
    OTH("Other");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
