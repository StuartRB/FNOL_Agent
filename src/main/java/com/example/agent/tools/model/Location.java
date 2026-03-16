package com.example.agent.tools.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
   private String street;
   private String city;
   private String zip;
   private String state;
}
