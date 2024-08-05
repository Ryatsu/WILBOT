package com.wilbot.WILBOT;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackBotController {
    
    @GetMapping("/")
    public String index() {
        return "index"; // This refers to src/main/resources/templates/index.html
    }
}
