package com.wilbot.WILBOT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilbot.WILBOT.model.Message;
import com.wilbot.WILBOT.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String userId, String messageContent) {
        Message message = new Message();
        message.setUserId(userId);
        message.setMessage(messageContent);
        message.setTimestamp(new java.util.Date()); // Or use an appropriate timestamp mechanism
        messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
