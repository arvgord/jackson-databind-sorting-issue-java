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
public class ThirdObject {

    @JsonAnyGetter
    private Map<String, Object> data = new LinkedHashMap<>();
    private String transactionId;

    ThirdObject() {}

    public ThirdObject(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonAnySetter
    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    public String getTransactionId() {
        return transactionId;
    }
}
