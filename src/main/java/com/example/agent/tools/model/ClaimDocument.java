package com.example.agent.tools.model;

import com.example.agent.constants.LossType;
import dev.langchain4j.model.output.structured.Description;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClaimDocument {
    @Description(value = "the status of the claim: NEW, OPEN or CLOSED")
    private String claimStatus;
    private String policyNumber;
    private LossType lossType;
    @Description("loss time stamp in UTC ISO8601 format")
    private String dateOfLoss;
    private Vehicle insuredVehicle;
    private Property insuredProperty;
    private List<Vehicle> otherInvolvedVehicles;
    private Location lossLocation;
    private Person reporter;
    private List<Person> otherInvolvedParticipants;
    private String policeReportNumber;
    private String policeDepartment;
    private String otherRelevantInformation;
    private String lossDescription;
    private boolean vehicleMatchedOnPolicy;
    private boolean policyActiveAtDateOfLoss;
}
