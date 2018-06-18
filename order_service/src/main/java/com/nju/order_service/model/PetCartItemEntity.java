package com.nju.order_service.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet_cart_item", schema = "pet_store_order", catalog = "")
public class PetCartItemEntity {
    private int id;
    private String petName;
    private int petNum;
    private double singlePrice;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pet_name")
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Basic
    @Column(name = "pet_num")
    public int getPetNum() {
        return petNum;
    }

    public void setPetNum(int petNum) {
        this.petNum = petNum;
    }

    @Basic
    @Column(name = "single_price")
    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetCartItemEntity that = (PetCartItemEntity) o;
        return id == that.id &&
                petNum == that.petNum &&
                Double.compare(that.singlePrice, singlePrice) == 0 &&
                userId == that.userId &&
                Objects.equals(petName, that.petName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, petName, petNum, singlePrice, userId);
    }
}
