package com.nju.order_service.restController.vo;

public class ResultVO {
    String message;
    int result;

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
