package com.nju.pet_service.restController.info;

public class PetInfo {
    String pet_name;
    int pet_store;
    double pet_price;
    int category_id;
    int id;

    String description;
    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

//    public void setStore(int store) {
//        this.pet_store = store;
//    }
//
//    public void setPrice(double price) {
//        this.pet_price = price;
//    }
//
//    public void setCid(int cid) {
//        this.category_id = cid;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getPet_name() {
//
//        return pet_name;
//    }
//
//    public int getStore() {
//        return store;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public int getCid() {
//        return cid;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getDesc() {
//        return desc;
//    }


    public String getPet_name() {
        return pet_name;
    }

    public int getPet_store() {
        return pet_store;
    }

    public void setPet_store(int pet_store) {
        this.pet_store = pet_store;
    }

    public double getPet_price() {
        return pet_price;
    }

    public void setPet_price(double pet_price) {
        this.pet_price = pet_price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
