package com.example.agent.service;

import com.example.agent.agents.ChatAgent;
import com.example.agent.model.ChatRequest;
import com.example.agent.model.ChatResponse;
import com.example.agent.mongo.model.ConversationDocument;
import com.example.agent.mongo.repository.ConversationRepository;
import com.example.agent.tools.ClaimTools;
import com.example.agent.tools.PolicyTools;
import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.agent.constants.ExtractionConstants.EXTRACTION_DATA_ELEMENTS;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatModel chatModel;
    private final ChatMemory chatMemory;
    private final ExtractionService extractionService;
    private final ConversationRepository conversationRepository;
    private final ClaimTools claimTools;
    private final PolicyTools policyTools;

    public ChatResponse chat(ChatRequest request) {

        var conversationDocument = getConversationDocument(request.getConversationId());

        extractionService.extract(request, conversationDocument);

        ChatAgent chatAgent = AgenticServices.agentBuilder(ChatAgent.class)
                .chatModel(chatModel)
                .chatMemory(chatMemory)
                .tools(claimTools, policyTools)
                .build();

        var response = chatAgent.chat(request.getMessage(),
                conversationDocument.getExtractedContext(),
                request.getConversationId());
        return new ChatResponse(response);
    }

    private ConversationDocument getConversationDocument(String conversationId) {
        var document = conversationRepository.findById(conversationId);
        if (document.isEmpty()) {
            var conversationDocument = ConversationDocument.builder()
                    .conversationId(conversationId)
                    .build();
            conversationRepository.save(conversationDocument);
            return conversationDocument;
        }
        return document.get();
    }
}
