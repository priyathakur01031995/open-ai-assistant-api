package com.amit.openaiclient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.amit.openaiclient.dto.*;

import java.io.IOException;

public class AssistantAIClient {

    //private final String assistantsUrl;
    private final String threadsUrl;
    private final String apiKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String SLASH = "/";

    public AssistantAIClient(Properties properties) {
        this.apiKey = properties.getProperty("openai.api.key");
        //this.assistantsUrl = properties.getProperty("openai.assistants.url");
        this.threadsUrl = properties.getProperty("openai.threads.url");
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    private String post(String url, Object body) throws Exception {
        String jsonBody = objectMapper.writeValueAsString(body);
        System.out.println("Request Body: " + jsonBody);
        HttpRequest request = getRequestWithHeaders(url).POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST " + url + " status " + response.statusCode()+" \n Response Body: "+response.body());
        if (response.statusCode() != 200) {
            String errorBody = response.body() != null ? response.body() : "No response body";
            throw new IOException("API call failed with status code: " + response.statusCode() + " and body: " + errorBody);
        }

        return response.body();
    }

    public ThreadResponseDTO createThread() throws Exception {
        String response = post(threadsUrl, "");
        return objectMapper.readValue(response, ThreadResponseDTO.class);
    }

    public MessageResponseDTO sendMessage(String threadId, String role, String content) throws Exception {
        String url = threadsUrl + SLASH + threadId + "/messages";
        MessageDTO dto = new MessageDTO(role, content);

        String response = post(url, dto);
        return objectMapper.readValue(response, MessageResponseDTO.class);
    }

    public RunResponseDTO runMessage(String threadId,RunRequestDTO runRequest) throws Exception {
        String url = threadsUrl + SLASH + threadId + "/runs";
        //RunRequestDTO runRequest = new RunRequestDTO(assistantId,maxPromptTokens,maxCompletionTokens, SchemaConstant.getSchemaJson(schemaNumber));

        String response = post(url, runRequest);
        return objectMapper.readValue(response, RunResponseDTO.class);
    }

    public RunResponseDTO getRunStatus(String threadId, String runId) throws Exception {
        String url = threadsUrl + SLASH + threadId + "/runs/" + runId;

        HttpRequest request = getRequestWithHeaders(url).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), RunResponseDTO.class);
    }


    public MessagesListResponseDTO getMessages(String threadId) throws Exception {
        String url = threadsUrl + SLASH + threadId + "/messages";

        HttpRequest request = getRequestWithHeaders(url).GET().build();
        System.out.println("Threads link for manual verification of requests: " + url);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Get Message Response: "+response.body());
        MessagesListResponseDTO messageResponse = objectMapper.readValue(response.body(), MessagesListResponseDTO.class);

        return messageResponse;

    }

    private HttpRequest.Builder getRequestWithHeaders(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Beta", "assistants=v2")
                .header("Content-Type", "application/json");
    }

   

}
