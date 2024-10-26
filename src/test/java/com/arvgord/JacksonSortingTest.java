package com.arvgord;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonSortingTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String JSON_INPUT = """
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

    private final String JSON_OUTPUT_SECOND = """
        {
            "transactionId": "test",
            "a": 1,
            "b": 2,
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

    private final String JSON_OUTPUT_THIRD = """
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
            ],
            "a": 1,
            "b": 2
        }
    """;

    private <T> void testSerializationDeserialization(String resultOutput, Class<T> clazz) throws Exception {
        T deserializedObject = objectMapper.readValue(JSON_INPUT, clazz);
        String serializedJson = objectMapper.writeValueAsString(deserializedObject);

        String expectedJson = objectMapper.readTree(resultOutput).toPrettyString();
        String actualJson = objectMapper.readTree(serializedJson).toPrettyString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testSerializationAndDeserializationForFirstObject() throws Exception {
        testSerializationDeserialization(JSON_INPUT, FirstObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForSecondObject() throws Exception {
        testSerializationDeserialization(JSON_OUTPUT_SECOND, SecondObject.class);
    }

    @Test
    public void testSerializationAndDeserializationForThirdObject() throws Exception {
        testSerializationDeserialization(JSON_OUTPUT_THIRD, ThirdObject.class);
    }
}
