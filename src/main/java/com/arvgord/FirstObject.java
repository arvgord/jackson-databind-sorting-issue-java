package com.arvgord;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE
)
public class FirstObject {

    @JsonAnySetter
    @JsonAnyGetter
    private Map<String, Object> data = new LinkedHashMap<>();

    public String getTransactionId() {
        return (String) data.get("transactionId");
    }
}