package com.amit.openaiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RunResponseDTO(
        String id,
        String object,
        @JsonProperty("created_at")
        long createdAt,
        @JsonProperty("assistant_id")
        String assistantId,
        @JsonProperty("thread_id")
        String threadId,
        String status,
        @JsonProperty("started_at")
        Long startedAt, // Using Long to handle null values
        @JsonProperty("expires_at")
        Long expiresAt,
        @JsonProperty("cancelled_at")
        Long cancelledAt,
        @JsonProperty("failed_at")
        Long failedAt,
        @JsonProperty("completed_at")
        Long completedAt,
        @JsonProperty("last_error")
        String lastError,
        String model,
        String instructions,
        List<Map<String, String>> tools, // List of tools with type details
        Map<String, Object> metadata, // Metadata is a generic map
        @JsonProperty("incomplete_details")
        String incompleteDetails, // Optional field, nullable
        Usage usage, // Nested usage object
        Double temperature, // Double for decimal values
        @JsonProperty("top_p")
        Double topP,
        @JsonProperty("max_prompt_tokens")
        Long maxPromptTokens,
        @JsonProperty("max_completion_tokens")
        Long maxCompletionTokens,
        @JsonProperty("truncation_strategy")
        TruncationStrategy truncationStrategy, // Nested truncation_strategy object
//        @JsonProperty("response_format")
//        String responseFormat,
        @JsonProperty("tool_choice")
        String toolChoice,
        @JsonProperty("parallel_tool_calls")
        boolean parallelToolCalls // Boolean field
) {
    // Nested record for Usage
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Usage(
            @JsonProperty("prompt_tokens")
            int promptTokens,
            @JsonProperty("completion_tokens")
            int completionTokens,
            @JsonProperty("total_tokens")
            int totalTokens
    ) {}

    // Nested record for TruncationStrategy
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TruncationStrategy(
            String type,
            @JsonProperty("last_messages")
            String lastMessages // This could be further defined if needed
    ) {}
}


