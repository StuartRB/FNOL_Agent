package com.example.agent.mongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
}