package com.example.agent.tools.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Policy {
   private String policyNumber;
   private String policyType;
   private List<Vehicle> insuredAssets;
   private String effectiveStartDate;
   private String effectiveEndDate;
   private List<Person> insuredPersons;
}
