package com.andrei.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionRequest implements Serializable {

    @JsonProperty("to")
    private String to;

    @JsonProperty("from")
    private String from;

    @JsonProperty("amount")
    private double amount;

    public ConversionRequest() {
    }

    public ConversionRequest(String to, String from, double amount){
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return this.to + "  " + this.from + " " + this.amount;
    }
}