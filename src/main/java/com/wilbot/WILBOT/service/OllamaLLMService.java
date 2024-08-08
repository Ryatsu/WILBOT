package com.wilbot.WILBOT.service;

import java.time.LocalDateTime;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilbot.WILBOT.model.Message;
import com.wilbot.WILBOT.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OllamaLLMService {

    private final OllamaChatModel chatModel;
    private final MessageRepository messageRepository;

    @Autowired
    public OllamaLLMService(OllamaChatModel chatModel, MessageRepository messageRepository) {
        this.chatModel = chatModel;
        this.messageRepository = messageRepository;
    }

    public String chat(String query) {
        if (chatModel == null) {
            log.error("chatModel is not initialized!");
            return "Chat model is not available.";
        }
        try {
            String response = chatModel.call(query);
            return response;
        } catch (Exception e) {
            log.error("Error occurred while calling chatModel: ", e);
            return "Sorry, an error occurred while processing your request.";
        }
    }

    public String chat(String userId, String query) {
        try {
            String response = chatModel.call(query);
            saveMessage(userId, query);
            return response;
        } catch (Exception e) {
            log.error("Error occurred while calling chatModel or saving message: ", e);
            return "Sorry, an error occurred while processing your request.";
        }
    }

    private void saveMessage(String userId, String message) {
        try {
            Message msg = new Message();
            msg.setUserId(userId);
            msg.setMessage(message);
            msg.setTimestamp(LocalDateTime.now());
            messageRepository.save(msg);
        } catch (Exception e) {
            log.error("Error occurred while saving message: ", e);
        }
    }
}
