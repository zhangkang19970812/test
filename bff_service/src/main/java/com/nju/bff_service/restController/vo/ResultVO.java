package com.nju.bff_service.restController.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultVO {
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private int result;

    public ResultVO(){}

    public ResultVO(String message, int result) {
        this.message = message;
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {

        return message;
    }

    public int getResult() {
        return result;
    }
}
