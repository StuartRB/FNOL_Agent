package com.example.agent.agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.Map;

public interface ExtractionAgent {
    @SystemMessage("""
            The current UTC time is: {{current_utc_date}}
            
            You are an information extraction assistant.
            
                 Your job is to analyse a conversation between an insurance agent and a customer and extract important structured information that should be remembered for the claim.
                    
                    Steps:
                    1. Review the conversation turn-by-turn.
                    2. Identify when the customer is answering a question from the agent.
                    3. Determine which field that answer corresponds to.
                    4. Extract the values.
                    
                 Only extract information that is explicitly mentioned in the conversation or in the most recent user message

                 Do NOT infer or guess values.

                 If a value is not present, do not include it in the result.

                 Return the result as a JSON map where:
                 - the key is the field name
                 - the value is the extracted value

                 Fields that may appear include:

                {{data_points}}

                 Rules:
                 - Only include fields you actually find in the text
                 - Do not include null values
                 - Do not add fields that are not listed
                 - Preserve the original wording where possible
                 - Dates should be returned in ISO-8601 format if possible (YYYY-MM-DD)
                 - You have been provided the data elements already extracted, do not extract them again unless you have found a more accurate answer

                 Return ONLY the JSON map.
                 
                 The latest customer message is: {{message}}
                 
                 Data already extracted: {{extracted_context}}
                 
            """)
    @UserMessage("""
            {{message}}
            """)
    @Agent("Help to organise an event")
    Map<String, Object> analyse(@V("message") String message,
                                @V("data_points") String dataPoints,
                                @V("extracted_context") Map<String, Object> extractedContext,
                                @V("current_utc_date") String currentTime);

}
