package com.amit.openaiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

/**
 *
 * @author kundankumar
 */
public class SchemaConstant {

    public static final String schemaString1 = """
        {
          "type": "object",
          "properties": {
            "1": {"type": "string"}
          },
          "required": ["1"],
          "additionalProperties": false
        }
    """;

    public static final String schemaString2 = """
            {
              "type": "object",
              "properties": {
                "1": {"type": "string"},
                "2": {"type": ["string", "null"]},
                "3": {"type": ["string", "null"]},
                "4": {"type": ["string", "null"]},
                "5": {"type": "string"},
                "6": {"type": "string"}
              },
              "required": ["1", "2", "3", "4", "5", "6"],
              "additionalProperties": false
            }
        """;

    public static final String schemaString3 = """
            {
              "type": "object",
              "properties": {
                "1": {"type": "string"}
              },
              "required": ["1"],
              "additionalProperties": false 
            }
        """;

    public static String schemaString4;

    
    public static Map<String, Object> getSchemaJson(int schemaNumber) throws Exception {
        String schemaString;
        if (schemaNumber == 1) {
            schemaString = schemaString1;
        } else if (schemaNumber == 2) {
            schemaString = schemaString2;
        } else if (schemaNumber == 3) {
            schemaString = schemaString3;
        } else if (schemaNumber == 4) {
            schemaString = schemaString4;
        } else {
            throw new RuntimeException("Invalid Schema number, Must be between 1 to 4");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> schemaMap = objectMapper.readValue(schemaString, Map.class);

        return Map.of(
                "type", "json_schema",
                "json_schema", Map.of(
                        "name", "structured_response",
                        "schema", schemaMap,
                        "strict", true
                )
        );
    }

}
