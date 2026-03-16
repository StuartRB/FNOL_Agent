package com.example.agent.mongo.repository;
import com.example.agent.mongo.model.ConversationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<ConversationDocument, String> {
}
