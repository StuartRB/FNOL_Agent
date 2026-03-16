package com.example.agent.tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Injury {
   private String bodyPart;
   private String injuryType;
}
