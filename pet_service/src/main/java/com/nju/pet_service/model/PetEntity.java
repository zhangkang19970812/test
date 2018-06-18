package com.nju.pet_service.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet", schema = "pet_store_pet", catalog = "")
public class PetEntity {
    private int id;
    private String petName;
    private double singlePrice;
    private String descript;
    private int categoryId;
    private Integer store;

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
    @Column(name = "single_price")
    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    @Basic
    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "store")
    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEntity petEntity = (PetEntity) o;
        return id == petEntity.id &&
                Double.compare(petEntity.singlePrice, singlePrice) == 0 &&
                categoryId == petEntity.categoryId &&
                Objects.equals(petName, petEntity.petName) &&
                Objects.equals(descript, petEntity.descript) &&
                Objects.equals(store, petEntity.store);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, petName, singlePrice, descript, categoryId, store);
    }
}
