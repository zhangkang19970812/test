package com.nju.order_service.dao;

import com.nju.order_service.model.PetOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDAO extends JpaRepository<PetOrderItemEntity, Integer>{

    @Query("select max(entity.id) from PetOrderEntity entity")
    Integer findMaxId();

    List<PetOrderItemEntity> findPetOrderItemEntitiesByOrderId(Integer orderId);
}
