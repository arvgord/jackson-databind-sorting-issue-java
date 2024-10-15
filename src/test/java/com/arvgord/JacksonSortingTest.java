package com.arvgord;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonSortingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String JSON_INPUT_FIRST = """
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

    private final String JSON_INPUT_SECOND_AND_THIRD = """
        {
            "transactionId": "test",
            "b": 2,
            "a": 1,
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

    private <T> void testSerializationDeserialization(String jsonInput, Class<T> clazz) throws Exception {
        T deserializedObject = objectMapper.readValue(jsonInput, clazz);
        String serializedJson = objectMapper.writeValueAsString(deserializedObject);

        String expectedJson = objectMapper.readTree(jsonInput).toPrettyString();
        String actualJson = objectMapper.readTree(serializedJson).toPrettyString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testSerializationAndDeserializationForFirstObject() throws Exception {
        testSerializationDeserialization(JSON_INPUT_FIRST, FirstObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForSecondObject() throws Exception {
        testSerializationDeserialization(JSON_INPUT_SECOND_AND_THIRD, SecondObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForThirdObject() throws Exception {
        testSerializationDeserialization(JSON_INPUT_SECOND_AND_THIRD, ThirdObject.class);
    }
}
