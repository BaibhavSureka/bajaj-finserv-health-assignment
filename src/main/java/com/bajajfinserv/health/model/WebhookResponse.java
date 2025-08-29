package com.bajajfinserv.health.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookResponse {

    @JsonProperty("webhook")
    private String webhook;

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("message")
    private String message;

    public WebhookResponse() {
    }

    // Getters and Setters
    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
