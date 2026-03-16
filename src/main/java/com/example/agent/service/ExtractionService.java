package com.example.agent.service;

import com.example.agent.agents.ExtractionAgent;
import com.example.agent.constants.ExtractionConstants;
import com.example.agent.model.ChatRequest;
import com.example.agent.mongo.model.ConversationDocument;
import com.example.agent.mongo.repository.ConversationRepository;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ExtractionService {

    private final ChatModel chatModel;
    private final ChatMemory chatMemory;
    private final ConversationRepository conversationRepository;

    public void extract(ChatRequest request, ConversationDocument conversationDocument) {

        ExtractionAgent extractionAgent = AgenticServices.agentBuilder(ExtractionAgent.class)
                .chatModel(chatModel)
                .chatMemory(chatMemory)
                .build();

        var response = extractionAgent.analyse( request.getMessage(),
                ExtractionConstants.EXTRACTION_DATA_ELEMENTS,
                conversationDocument.getExtractedContext(),
                java.time.LocalDate.now().toString());

        if (!Objects.isNull(response) || !response.isEmpty()){
             var extractions =  conversationDocument.getExtractedContext();
             extractions.putAll(response);
             conversationDocument.setExtractedContext(extractions);
             conversationRepository.save(conversationDocument);
        }
        System.out.println("extracted: " + response);

    }
}
