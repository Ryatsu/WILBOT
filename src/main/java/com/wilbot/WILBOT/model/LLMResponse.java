
package com.wilbot.WILBOT.model;

import lombok.Data;

@Data
public class LLMResponse {
    private String status;
    private String response;

    // No-argument constructor
    public LLMResponse() {
    }

    // All-argument constructor
    public LLMResponse(String status, String response) {
        this.status = status;
        this.response = response;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for response
    public String getResponse() {
        return response;
    }

    // Setter for response
    public void setResponse(String response) {
        this.response = response;
    }
}
