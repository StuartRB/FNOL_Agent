package com.example.agent.tools;

import com.example.agent.mongo.repository.ConversationRepository;
import com.example.agent.tools.model.ClaimDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClaimTools {

    private final ObjectMapper objectMapper;
    private final ConversationRepository repository;

    @Tool("creates a new claim record in the claims system and returns the newly generated claim number")
    public String createNewClaim(String conversationId, ClaimDocument claimDocument) {
        // save to conversation document
        saveClaimDocument(conversationId, claimDocument);

        //TODO send to claim system

        // for logging
        try {
            System.out.println("About to create claim for conversationId: " + conversationId +"\n" + objectMapper.writeValueAsString(claimDocument));
        } catch (Exception ex) {
            System.out.println("meh. " + ex.getMessage());
        }
        return "Claim-0099991";
    }

    private void saveClaimDocument(String conversationId, ClaimDocument claimDocument) {
        repository.findById(conversationId)
                .ifPresent(document -> {
                    document.setClaimDocument(claimDocument);
                    repository.save(document);
                });
    }
}
