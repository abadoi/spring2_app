package com.andrei.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ConversionResponse implements Serializable {

    @JsonProperty("to")
    private String to;

    @JsonProperty("from")
    private String from;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("converted")
    private double converted;


    public ConversionResponse() {
    }


    public ConversionResponse(String to, String from, double amount, double converted){
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.converted = converted;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setConverted(double converted) {
        this.converted = converted;
    }

    @Override
    public String toString() {
        return this.to + "  " + this.from + " " + this.amount + " " + this.converted;
    }
}