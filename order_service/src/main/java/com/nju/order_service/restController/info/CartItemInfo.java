package com.nju.order_service.restController.info;

public class CartItemInfo {
    int id;
    int userId;
    String pet_name;
    double single_price;
    int pet_num;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public void setSingle_price(double single_price) {
        this.single_price = single_price;
    }

    public void setPet_num(int pet_num) {
        this.pet_num = pet_num;
    }

    public int getId() {

        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getPet_name() {
        return pet_name;
    }

    public double getSingle_price() {
        return single_price;
    }

    public int getPet_num() {
        return pet_num;
    }
}
