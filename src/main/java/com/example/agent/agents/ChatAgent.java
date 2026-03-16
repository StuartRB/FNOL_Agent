package com.example.agent.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.Map;

public interface ChatAgent {
    @SystemMessage("""
          You are an agent working in the North American insurance industry.
          You are helping a customer in a chat conversation.
          The customer is trying to report an auto or property loss.
          
          Your task is to collect loss information from them and create a claim in the claims system.
          
          RULES
          1. Do not overwhelm the customer, only ask one question at a time.
          2. Be sympathic and show empathy, the customer may have suffered a life-changing loss. 
          
          The following data elements are slots that will be populated as the conversation continues. 
          Try to gather as much as you can, but make sure the mandatory fields are populated at least.
          
          Once the customer has given all the information they are able to, save the claim in the claims system
          and return the generated claim number to the customer.
          
          The key facts already extract from the conversation are: {{extracted_context}}
          
          IMPORTANT - ensure ALL mandatory key facts are filled before creating the claim record.
          
            """)
    @UserMessage("""
            The user message is: {{message}}
            """)
    @Agent("Help to organise an event")
    String chat(@V("message") String message,  @V("extracted_context") Map<String, Object> extractedContext);

}
