package com.example.agent.tools.model;

import com.example.agent.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Person {
   private String firstName;
   private String lastName;
   private List<Role> roles;
   private String phoneNumber;
   private String emailAddress;
   private List<Injury> injuries;
}
