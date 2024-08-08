package com.wilbot.WILBOT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilbot.WILBOT.model.LLMRequest;
import com.wilbot.WILBOT.service.OllamaLLMService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/chatbot")
public class LLMRestController {

    private final OllamaLLMService ollamaLLMService;

    public LLMRestController(OllamaLLMService ollamaLLMService) {
        this.ollamaLLMService = ollamaLLMService;
    }

    @PostMapping("/respond")
    public ResponseEntity<String> chat(@RequestBody LLMRequest request) {
        String chatResponse = ollamaLLMService.chat(request.getUserId(), request.getQuery());
        return ResponseEntity.ok(chatResponse);
    }

    @GetMapping("/start")
    public ResponseEntity<String> start() {
        return ResponseEntity.ok("Hi! I'm Wilbot. How can I help you today?");
    }
}
