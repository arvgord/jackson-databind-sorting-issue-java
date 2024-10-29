package com.arvgord;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FourthObject {

    private final String transactionId;
    private final Map<String, Object> data;

    @JsonCreator
    public FourthObject(
            @JsonProperty("transactionId") String transactionId,
            @JsonAnySetter @JsonProperty("data") Map<String, Object> data
    ) {
        this.transactionId = transactionId;
        this.data = data;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return this.data;
    }
}
