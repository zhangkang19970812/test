package com.nju.order_service.dao;

import com.nju.order_service.model.PetOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<PetOrderEntity, Integer>{
    @Query("select max(entity.id) from PetOrderEntity entity")
    Integer findMaxId();

    List<PetOrderEntity> findPetOrderEntitiesByUserId(Integer userId);
}
