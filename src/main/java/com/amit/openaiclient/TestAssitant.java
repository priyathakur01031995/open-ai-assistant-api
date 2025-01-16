package com.amit.openaiclient;

import com.amit.openaiclient.dto.*;

public class TestAssitant {

    public void testAssitant1() {
        boolean fileSearch = false;
        OpenAiApiUtil openAiApiUtil = new OpenAiApiUtil();
        try {
            //*** Call the AI Assitant function
            RunResponseDTO runResponse = openAiApiUtil.runAssistantFunction1("Query about vendor", "Avacell Inc", "Roofing and general contracting services", fileSearch);
            System.out.println("runResponse-> maxPromptTokens: " + runResponse.maxPromptTokens());
            System.out.println("runResponse-> maxCompletionTokens: " + runResponse.maxCompletionTokens());
            RunResponseDTO.Usage usage = runResponse.usage();
            if (usage != null) {
                System.out.println("runResponse-> TotalTokens: " + runResponse.usage().totalTokens());
                System.out.println("runResponse-> CompletionTokens: " + runResponse.usage().completionTokens());
                System.out.println("runResponse-> PromptTokens: " + runResponse.usage().promptTokens());
            }

            //*** Fetch the message, it will wait till run is completed, then it will return message
            MessagesListResponseDTO assistantDto = openAiApiUtil.getAssitantMessages(runResponse.threadId(), runResponse.id());
            System.out.println("Assistant1DTO : " + assistantDto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testAssitant2() {
        boolean fileSearch = true;
        OpenAiApiUtil openAiApiUtil = new OpenAiApiUtil();
        try {
            //*** Call the AI Assitant function
            RunResponseDTO runResponse = openAiApiUtil.runAssistantFunction2("Restaurant", "A101", "Dominos Pizza", fileSearch);
            System.out.println("runResponse-> maxPromptTokens: " + runResponse.maxPromptTokens());
            System.out.println("runResponse-> maxCompletionTokens: " + runResponse.maxCompletionTokens());
            RunResponseDTO.Usage usage = runResponse.usage();
            if (usage != null) {
                System.out.println("runResponse-> TotalTokens: " + runResponse.usage().totalTokens());
                System.out.println("runResponse-> CompletionTokens: " + runResponse.usage().completionTokens());
                System.out.println("runResponse-> PromptTokens: " + runResponse.usage().promptTokens());
            }

            //*** Fetch the message, it will wait till run is completed, then it will return message
            MessagesListResponseDTO assistantDto = openAiApiUtil.getAssitantMessages(runResponse.threadId(), runResponse.id());
            System.out.println("Assistant2DTO : " + assistantDto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testAssitant3() {
        boolean fileSearch = false;
        OpenAiApiUtil openAiApiUtil = new OpenAiApiUtil();
        try {
            //*** Call the AI Assitant function
            RunResponseDTO runResponse = openAiApiUtil.runAssistantFunction3("Just a user query", fileSearch);
            System.out.println("runResponse-> maxPromptTokens: " + runResponse.maxPromptTokens());
            System.out.println("runResponse-> maxCompletionTokens: " + runResponse.maxCompletionTokens());
            RunResponseDTO.Usage usage = runResponse.usage();
            if (usage != null) {
                System.out.println("runResponse-> TotalTokens: " + runResponse.usage().totalTokens());
                System.out.println("runResponse-> CompletionTokens: " + runResponse.usage().completionTokens());
                System.out.println("runResponse-> PromptTokens: " + runResponse.usage().promptTokens());
            }

            boolean runDone = openAiApiUtil.isRunDone(runResponse.threadId(), runResponse.id());
            System.out.println("isRunDone: " + runDone);

            //*** Fetch the message, it will wait till run is completed, then it will return message
            MessagesListResponseDTO assistantDto = openAiApiUtil.getAssitantMessages(runResponse.threadId(), runResponse.id());
            System.out.println("Assistant3DTO : " + assistantDto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void testAssitant4() {
        boolean fileSearch = false;
        OpenAiApiUtil openAiApiUtil = new OpenAiApiUtil();
        try {
            //*** Call the AI Assitant function
            RunResponseDTO runResponse = openAiApiUtil.runAssistantFunction4("Lease agreement text", "2024-12-12", fileSearch);
            System.out.println("runResponse-> maxPromptTokens: " + runResponse.maxPromptTokens());
            System.out.println("runResponse-> maxCompletionTokens: " + runResponse.maxCompletionTokens());
            RunResponseDTO.Usage usage = runResponse.usage();
            if (usage != null) {
                System.out.println("runResponse-> TotalTokens: " + runResponse.usage().totalTokens());
                System.out.println("runResponse-> CompletionTokens: " + runResponse.usage().completionTokens());
                System.out.println("runResponse-> PromptTokens: " + runResponse.usage().promptTokens());
            }

            //*** Fetch the message, it will wait till run is completed, then it will return message
            MessagesListResponseDTO assistantDto = openAiApiUtil.getAssitantMessages(runResponse.threadId(), runResponse.id());
            System.out.println("Assistant4DTO : " + assistantDto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TestAssitant testAssitant = new TestAssitant();
        testAssitant.testAssitant1();
        //testAssitant.testAssitant2();
        //testAssitant.testAssitant3();

        //testAssitant.testAssitant4();
    }

}
