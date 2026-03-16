package com.example.agent.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

        @Bean
        ChatMemory chatMemory() {
            return MessageWindowChatMemory.withMaxMessages(50);
        }

        @Bean
        ChatModelListener chatModelListener() {
            return new ChatModelListener() {

                private static final Logger log = LoggerFactory.getLogger(ChatConfig.class);

                @Override
                public void onRequest(ChatModelRequestContext requestContext) {
//                    log.info("onRequest(): {}", requestContext.chatRequest());
                }

                @Override
                public void onResponse(ChatModelResponseContext responseContext) {
//                    log.info("onResponse(): {}", responseContext.chatResponse());
                }

                @Override
                public void onError(ChatModelErrorContext errorContext) {
                    log.info("onError(): {}", errorContext.error().getMessage());
                }
            };

    }
}
