package com.example.agent.mongo.model;

import com.example.agent.tools.model.ClaimDocument;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Document(collection = "conversation")
public class ConversationDocument {
    @Id
    private String conversationId;
    //    private List<InteractionDocument> interactions;
    private Map<String, Object> extractedContext;

    public Map<String, Object> getExtractedContext() {
        return extractedContext == null ? new HashMap<>() : extractedContext;
    }
    private ClaimDocument claimDocument;
    @CreatedDate
    private Instant createdTs;
    @LastModifiedDate
    private Instant updatedTs;
}