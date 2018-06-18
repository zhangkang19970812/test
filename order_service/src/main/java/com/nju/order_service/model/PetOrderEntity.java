package com.nju.order_service.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet_order", schema = "pet_store_order", catalog = "")
public class PetOrderEntity {
    private int id;
    private int status;
    private double price;
    private int userId;
    private String description;
    private String bill;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "bill")
    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetOrderEntity that = (PetOrderEntity) o;
        return id == that.id &&
                status == that.status &&
                Double.compare(that.price, price) == 0 &&
                userId == that.userId &&
                Objects.equals(description, that.description) &&
                Objects.equals(bill, that.bill);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, status, price, userId, description, bill);
    }
}
