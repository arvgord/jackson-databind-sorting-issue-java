package com.arvgord;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE
)
public class SecondObject {

    private final String transactionId;
    @JsonAnySetter
    @JsonAnyGetter
    private final Map<String, Object>  data;


    @JsonCreator
    public SecondObject(@JsonProperty("transactionId") String transactionId) {
        this.transactionId = transactionId;
        this.data = new LinkedHashMap<>();
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}