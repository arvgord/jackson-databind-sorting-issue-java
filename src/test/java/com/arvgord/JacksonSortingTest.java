package com.arvgord;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonSortingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String INPUT_JSON = """
        {
            "b": 2,
            "a": 1,
            "transactionId": "test",
            "c": [
                {
                    "id": "3",
                    "value": "c"
                },
                {
                    "id": "1",
                    "value": "a"
                },
                {
                    "id": "2",
                    "value": "b"
                }
            ]
        }
    """;

    private final String SECOND_UNEXPECTED_JSON_OUTPUT  = """
        {
            "transactionId": "test",
            "c": [
                {
                    "id": "3",
                    "value": "c"
                },
                {
                    "id": "1",
                    "value": "a"
                },
                {
                    "id": "2",
                    "value": "b"
                }
            ]
        }
    """;

    private final String THIRD_UNEXPECTED_JSON_OUTPUT = """
        {
            "transactionId": "test"
        }
    """;

    private <T> void testSerializationDeserialization(String outputResult, Class<T> clazz) throws Exception {
        T deserializedObject = objectMapper.readValue(INPUT_JSON, clazz);
        String serializedJson = objectMapper.writeValueAsString(deserializedObject);

        String expectedJson = objectMapper.readTree(outputResult).toPrettyString();
        String actualJson = objectMapper.readTree(serializedJson).toPrettyString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testSerializationAndDeserializationForFirstObject() throws Exception {
        testSerializationDeserialization(INPUT_JSON, FirstObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForSecondObject() throws Exception {
        testSerializationDeserialization(SECOND_UNEXPECTED_JSON_OUTPUT, SecondObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForThirdObject() throws Exception {
        testSerializationDeserialization(THIRD_UNEXPECTED_JSON_OUTPUT, ThirdObject.class);
    }
}
