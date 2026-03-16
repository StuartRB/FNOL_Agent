package com.example.agent.tools;

import com.example.agent.tools.model.Person;
import com.example.agent.tools.model.Policy;
import com.example.agent.tools.model.Vehicle;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PolicyTools {

    @Tool("returns policy information for a given policy number")
    public Policy getPolicy(String policyNumber) {
        return Policy.builder()
                .policyNumber(policyNumber)
                .policyType("Auto")
                .effectiveStartDate("2026-01-31T00:00")
                .effectiveEndDate("2026-12-31T00:00")
                .insuredPersons(List.of(new Person("Ian","Cheeseman", Collections.emptyList(),"111-222-3333","ian@cheeseman.com",Collections.emptyList())))
                .insuredAssets(List.of(new Vehicle("01","Ford","Escort","2020","Red", null,null,null)))
                .build();
    }

}
