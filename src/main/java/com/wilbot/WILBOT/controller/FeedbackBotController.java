package com.wilbot.WILBOT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilbot.WILBOT.MessageRequest;
import com.wilbot.WILBOT.model.Message;
import com.wilbot.WILBOT.service.MessageService;

@RestController
@RequestMapping("/api")
public class FeedbackBotController {

    @Autowired
    private MessageService messageService;

    // @PostMapping("/messages")
    // public void saveMessage(@RequestBody MessageRequest messageRequest) {
    // messageService.saveMessage(messageRequest.getUserId(),
    // messageRequest.getMessage());
    // }

    @PostMapping("/messages")
    public ResponseEntity<String> saveMessage(@RequestBody MessageRequest messageRequest) {
        try {
            messageService.saveMessage(messageRequest.getUserId(), messageRequest.getMessage());
            return ResponseEntity.ok("Message saved successfully.");
        } catch (Exception e) {
            // Log the error and return a proper response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save message.");
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }
}