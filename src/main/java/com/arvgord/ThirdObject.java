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
public class ThirdObject {

    private final String transactionId;
    @JsonAnySetter
    @JsonAnyGetter
    private final Map<String, Object> data;


    @JsonCreator
    public ThirdObject(
            @JsonProperty("transactionId") String transactionId,
            @JsonProperty("data") Map<String, Object> data
    ) {
        this.transactionId = transactionId;
        this.data = data;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}
