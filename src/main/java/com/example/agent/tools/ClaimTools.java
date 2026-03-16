package com.example.agent.tools;

import com.example.agent.tools.model.ClaimDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClaimTools {

    private final ObjectMapper objectMapper;

    @Tool("creates a new claim record in the claims system and returns the newly generated claim number")
    public String createNewClaim(ClaimDocument claimDocument) {
        // send to claims system

        try {
            System.out.println("About to create claim: \n" + objectMapper.writeValueAsString(claimDocument));
        } catch (Exception ex) {
            System.out.println("meh. " + ex.getMessage());
        }
        return "Claim-0099991";
    }
}
