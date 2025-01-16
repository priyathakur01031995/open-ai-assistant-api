package com.amit.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RunRequestDTO {

    @JsonProperty("assistant_id")
    private String assistantId;
    @JsonProperty("max_prompt_tokens")
    private long maxPromptTokens;
    @JsonProperty("max_completion_tokens")
    private long maxCompletionTokens;
    @JsonProperty("tools")
    private Tool tools; // Nested usage object

    @JsonProperty("response_format")
    private Map<String, Object> responseFormat;

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public long getMaxPromptTokens() {
        return maxPromptTokens;
    }

    public void setMaxPromptTokens(long maxPromptTokens) {
        this.maxPromptTokens = maxPromptTokens;
    }

    public long getMaxCompletionTokens() {
        return maxCompletionTokens;
    }

    public void setMaxCompletionTokens(long maxCompletionTokens) {
        this.maxCompletionTokens = maxCompletionTokens;
    }

    public Tool getTools() {
        return tools;
    }

    public void setTools(Tool tools) {
        this.tools = tools;
    }

    public Map<String, Object> getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(Map<String, Object> responseFormat) {
        this.responseFormat = responseFormat;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tool {

        @JsonProperty("type")
        private String type; // possible value file_search , code_interpreter, function

        public Tool(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}
