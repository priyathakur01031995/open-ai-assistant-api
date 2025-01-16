package com.amit.openaiclient.model;

public enum GPTModel {
     GPT_4_O("gpt4o"), 
     GPT4("gpt-4");

    private final String modelName;

    GPTModel(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return modelName;
    }
}
