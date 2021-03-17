package com.andrei.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyAPIResponse {

    @JsonProperty("rates")
    public Map<String, Double> rates;
//
//    public JSONObject getRates() {
//        return rates;
//    }
}