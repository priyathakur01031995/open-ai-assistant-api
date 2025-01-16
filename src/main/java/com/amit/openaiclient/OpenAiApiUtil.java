package com.amit.openaiclient;

import com.amit.openaiclient.dto.*;
import com.amit.openaiclient.dto.RunRequestDTO.Tool;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.io.InputStream;
import java.io.ByteArrayOutputStream; import java.io.IOException;
import static java.lang.Thread.sleep;


public class OpenAiApiUtil {

    private Properties properties;
    private AssistantAIClient client;
    private final ObjectMapper objectMapper;
    private static long DELAY = 3; // wait for 3 seconds between status checks

    /**
     * Constructor to initialise the properties file
     */
    public OpenAiApiUtil() {
        
        this.objectMapper = new ObjectMapper();
        properties = new Properties();
        
        try (InputStream input = OpenAiApiUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
            // *** Init schema 4 from file
            SchemaConstant.schemaString4 = readInputStreamToString(OpenAiApiUtil.class.getClassLoader().getResourceAsStream("schemastring4.json"));

            client = new AssistantAIClient(properties);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private  String readInputStreamToString(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1)
        { result.write(buffer, 0, length); }
        return result.toString("UTF-8"); // Specify the character encoding
    }

    public RunResponseDTO runAssistantFunction1(String userText, String vendor, String vendorDescription, boolean fileSearch) throws Exception {
        String message = userText + ",[{\"Vendor\":\"" + vendor + "\",\"VendorDescription\":\"" + vendorDescription + "\"}]";
        long maxPromptTokens = 1000;
        long maxCompletionTokens =50;

        ThreadResponseDTO thread = client.createThread();
        System.out.println("Thread Created, ThreadId: "+thread.id());
        
        MessageResponseDTO msgSendResp = client.sendMessage(thread.id(), "user", message);
        System.out.println("Message Added, MessageId: "+msgSendResp.id());
        
        RunRequestDTO runReq = new RunRequestDTO();
        runReq.setAssistantId(properties.getProperty("openai.assistants.id1"));
        runReq.setMaxPromptTokens(maxPromptTokens);
        runReq.setMaxCompletionTokens(maxCompletionTokens);
        // *** If file search enabled set in request
         if(fileSearch){
          runReq.setTools(new Tool("file_search")); //If file search, then pass it
        }
        runReq.setResponseFormat(SchemaConstant.getSchemaJson(1));
        
        RunResponseDTO run = client.runMessage(thread.id(), runReq);
        System.out.println("Thread run submitted, Run ID: "+run.id());
        return run;

    }

    // Assistant Function 2
    public RunResponseDTO runAssistantFunction2(String userText, String unit, String tenant, boolean fileSearch) throws Exception {
        String message = userText + ",[{\"Unit\":\"" + unit + "\",\"Tenant\":\"" + tenant + "\"}]";
        long maxPromptTokens = 10000;
        long maxCompletionTokens =50;
        
        ThreadResponseDTO thread = client.createThread();
        System.out.println("Thread Created, ThreadId: "+thread.id());
        
        MessageResponseDTO msgSendResp = client.sendMessage(thread.id(), "user", message);
        System.out.println("Message Added, MessageId: "+msgSendResp.id());
        
        RunRequestDTO runReq = new RunRequestDTO();
        runReq.setAssistantId(properties.getProperty("openai.assistants.id2"));
        runReq.setMaxPromptTokens(maxPromptTokens);
        runReq.setMaxCompletionTokens(maxCompletionTokens);
        // *** If file search enabled set in request
        if(fileSearch){
          runReq.setTools(new Tool("file_search")); //If file search, then pass it
        }
        runReq.setResponseFormat(SchemaConstant.getSchemaJson(2));
        
        RunResponseDTO run = client.runMessage(thread.id(), runReq);
        System.out.println("Thread run submitted, Run ID: "+run.id());
        return run;
    }

    // Assistant Function 3
    public RunResponseDTO runAssistantFunction3(String userText, boolean fileSearch) throws Exception {
        String message = userText;
        long maxPromptTokens = 1000;
        long maxCompletionTokens =50;

        ThreadResponseDTO thread = client.createThread();
        System.out.println("Thread Created, ThreadId: "+thread.id());
        
        MessageResponseDTO msgSendResp = client.sendMessage(thread.id(), "user", message);
        System.out.println("Message Added, MessageId: "+msgSendResp.id());
        
        RunRequestDTO runReq = new RunRequestDTO();
        runReq.setAssistantId(properties.getProperty("openai.assistants.id3"));
        runReq.setMaxPromptTokens(maxPromptTokens);
        runReq.setMaxCompletionTokens(maxCompletionTokens);
        // *** If file search enabled set in request
         if(fileSearch){
          runReq.setTools(new Tool("file_search")); //If file search, then pass it
        }
        runReq.setResponseFormat(SchemaConstant.getSchemaJson(3));
        
        RunResponseDTO run = client.runMessage(thread.id(), runReq);
        System.out.println("Thread run submitted, Run ID: "+run.id());
        return run;
    }

    // Assistant Function 4
    public RunResponseDTO runAssistantFunction4(String leaseText, String deliveryDate, boolean fileSearch) throws Exception {
        String message = String.format("{\"lease_text\":\"%s\",\"delivery_date\":\"%s\"}", leaseText, deliveryDate);
        long maxPromptTokens = 50000;
        long maxCompletionTokens =500;

        ThreadResponseDTO thread = client.createThread();
        System.out.println("Thread Created, ThreadId: "+thread.id());
        
        MessageResponseDTO msgSendResp = client.sendMessage(thread.id(), "user", message);
        System.out.println("Message Added, MessageId: "+msgSendResp.id());
        
        RunRequestDTO runReq = new RunRequestDTO();
        runReq.setAssistantId(properties.getProperty("openai.assistants.id4"));
        runReq.setMaxPromptTokens(maxPromptTokens);
        runReq.setMaxCompletionTokens(maxCompletionTokens);
        // *** If file search enabled set in request
         if(fileSearch){
          runReq.setTools(new Tool("file_search")); //If file search, then pass it
        }
        runReq.setResponseFormat(SchemaConstant.getSchemaJson(4));
        
        RunResponseDTO run = client.runMessage(thread.id(), runReq);
        System.out.println("Thread run submitted, Run ID: "+run.id());
        return run;
    }
    

    
    public MessagesListResponseDTO getAssitantMessages(String threadId, String runId) throws Exception {
        // *** Check Run status and finished before calling message
        waitUntilRunIsFinished(threadId,runId);
        // *** Run finished, Now get the messages
        MessagesListResponseDTO messageResponse = client.getMessages(threadId);
        return messageResponse;
    }
    
    
    private void waitUntilRunIsFinished(String threadId, String runId) throws InterruptedException {
        while (!isRunDone(threadId, runId)) {
            sleep(DELAY * 1000);
        }
    }
  
    
    //*** Helper function to check run is completed
    public  boolean isRunDone(String threadId, String runId) {
        RunResponseDTO status;
        try {
            status = client.getRunStatus(threadId, runId);
            System.out.println("Status of your run is currently " + status);
            return isRunStateFinal(status);
        } catch (Exception e) {
            System.err.println("Failed to get run state, will retry..." + e);
            e.printStackTrace();
            return false;
        }
    }

    //*** Helper function to fetch Message
    private boolean isRunStateFinal(RunResponseDTO runResponseDTO) {
        List<String> finalStates = List.of("cancelled", "failed", "completed", "expired"); //I consider these states as final
        String runStatus = Optional.of(runResponseDTO).map(RunResponseDTO::status).orElse("unknown").toLowerCase();
        return finalStates.contains(runStatus);
    }
    
     private static void log(MessagesListResponseDTO messages) {
        messages.data().forEach(System.out::println);
    }

    private static String getValueFromMessage(MessagesListResponseDTO messageResponseListDTO) {

        if (messageResponseListDTO != null && messageResponseListDTO.data() != null) {
            log(messageResponseListDTO);
        } else {
            System.out.println("MessageResponseListDTO is null, Check MessageResponse: " + messageResponseListDTO);
        }

        String jsonSchemaValue = null;
        for (MessageResponseDTO ml : messageResponseListDTO.data()) {
            if ("assistant".equalsIgnoreCase(ml.role())) {
                List<MessageResponseDTO.Content> contentList = ml.content();
                if (contentList != null && !contentList.isEmpty()) {
                    MessageResponseDTO.Content.Text text = contentList.get(0).text();
                    if (text != null) {
                        jsonSchemaValue = text.value();
                        System.out.println("Extracted JSON Object Value: " + jsonSchemaValue);
                    }
                }
                break;
            }
        }
        if (jsonSchemaValue == null) {
            throw new RuntimeException("Json Schema Value is null Or Still thread is running, Check MessageResponse");
        }

        return jsonSchemaValue;
    }
    
}
