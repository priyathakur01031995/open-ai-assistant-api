package com.amit.openaiclient.assitant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author kundankumar
 */
public class AssistantResponse<T> {

    @JsonProperty("json_message")
    private T jsonMessage;

    @JsonProperty("token_info")
    private JsonNode tokenInfo;

    @JsonProperty("assistant_info")
    private JsonNode assistantInfo;

    public T getJsonMessage() {
        return jsonMessage;
    }

    public void setJsonMessage(T jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public JsonNode getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(JsonNode tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public JsonNode getAssistantInfo() {
        return assistantInfo;
    }

    public void setAssistantInfo(JsonNode assistantInfo) {
        this.assistantInfo = assistantInfo;
    }
    // Optional: Add a method to extract specific token usage fields

    public String getTotalTokens() {
        return tokenInfo != null ? tokenInfo.get("total_tokens").asText() : null;
    }

    public String getPromptTokens() {
        return tokenInfo != null ? tokenInfo.get("prompt_tokens").asText() : null;
    }

    public String getCompletionTokens() {
        return tokenInfo != null ? tokenInfo.get("completion_tokens").asText() : null;
    }
}
